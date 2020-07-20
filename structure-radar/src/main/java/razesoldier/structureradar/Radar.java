/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * http://www.gnu.org/copyleft/gpl.html
 */

package razesoldier.structureradar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import razesoldier.esi.character.NotificationModel;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.error.Non200CodeException;
import razesoldier.esi.sso.FetchProtectedResourceException;
import razesoldier.esi.sso.GetAccessTokenException;
import razesoldier.esi.sso.SSOLogin;
import razesoldier.esi.universe.GetItemTypeInfo;
import razesoldier.esi.universe.GetStructureInfo;
import razesoldier.esi.universe.GetSystemInfo;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 程序大部分生命周期在此。
 */
class Radar {
    private final List<Watcher> watchers;
    // 监视的间隔，以毫秒为单位（默认1分钟）
    private final Integer sleepInterval;
    private final Logger logger;

    Radar(@NotNull List<Watcher> watchers, @Nullable Integer sleepInterval, @NotNull Logger logger) {
        this.watchers = watchers;
        this.sleepInterval = Objects.requireNonNullElse(sleepInterval, 1000 * 30);
        this.logger = logger;
    }

    /**
     * 主要的运行地方。此方法内部有无限循环。
     */
    void run() {
        var context = new Object() {
            Instant lastCatchTime = Instant.now();
        };
        QQPusher pusher = new QQPusher();
        ExecutorService watchExecutor = Executors.newCachedThreadPool();
        Runnable runnable = () -> {
            List<Future<WatchReport>> futures = new ArrayList<>();
            final Instant finalLastWatchTime = context.lastCatchTime; // 用于传入lambda
            Instant currentTime = Instant.now(); // 在拉取ESI前记录当前的时间为这次的监视时间
            // Note：如果在记录当前时间和真正获取到ESI数据的期间刷新了晚于lastCatchTime的通知，会在下次监视周期里重新被监视到
            watchers.forEach(watcher -> {
                WatchTask task = new WatchTask(watcher, finalLastWatchTime);
                futures.add(watchExecutor.submit(task));
            });

            Set<NotificationModel> notificationModelSet = new HashSet<>();
            // 遍历所有的监视报告
            futures.forEach(watchReportFuture -> {
                WatchReport report;
                try {
                    report = watchReportFuture.get(2, TimeUnit.MINUTES);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return;
                } catch (TimeoutException e) {
                    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Z"));
                    String nowTimeText = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(now);
                    System.out.println(String.format("[%s] %s", nowTimeText, e));
                    return;
                }
                notificationModelSet.addAll(report.getNewNotifications());
            });

            // 如果这次监视周期没有通知则立即结束当前的周期
            if (notificationModelSet.size() == 0) {
                sleep();
                return;
            }

            context.lastCatchTime = currentTime; // 只有在当前监视周期监视到相关通知才会把lastCatchTime设置为当前监视周期的开始时间

            notificationModelSet.forEach(notificationModel -> {
                final String text = notificationModel.getText();

                Pattern pSystemName = Pattern.compile("solarsystemID:\\s([0-9]*)");
                Matcher matcher = pSystemName.matcher(text);
                String systemName;
                if (matcher.find()) {
                    GetSystemInfo getSystemInfo = new GetSystemInfo();
                    try {
                        systemName = getSystemInfo.query(Integer.valueOf(matcher.group(1))).getName();
                    } catch (HttpRequestException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        systemName = "??";
                    }
                } else {
                    systemName = "??";
                }

                Pattern pStructureType = Pattern.compile("structureTypeID:\\s([0-9]*)");
                matcher = pStructureType.matcher(text);
                String structureType;
                if (matcher.find()) {
                    String type = matcher.group(1);
                    switch (type) {
                        case "35841":
                            structureType = "跳桥";
                            break;
                        // 如果没有匹配，则请求ESI拉取类型名称
                        default:
                            GetItemTypeInfo query = new GetItemTypeInfo();
                            try {
                                structureType = query.query(Integer.valueOf(type)).name;
                            } catch (HttpRequestException e) {
                                // @TODO: 日志
                                structureType = "??";
                            }
                    }
                } else {
                    structureType = "??";
                }

                String attacker;
                Pattern pAllianceName = Pattern.compile("allianceName:\\s(.*)");
                matcher = pAllianceName.matcher(text);
                if (matcher.find()) {
                    attacker = matcher.group(1);
                } else {
                    Pattern pCorpName = Pattern.compile("corpName:\\s(.*)");
                    matcher = pCorpName.matcher(text);
                    if (matcher.find()) {
                        attacker = matcher.group(1);
                    } else {
                        attacker = "??";
                        // @TODO: 日志
                    }
                }

                String structure;
                Pattern pStructureID = Pattern.compile("structureID:\\s&id001\\s([0-9]*)");
                matcher = pStructureID.matcher(text);
                if (matcher.find()) {
                    SSOLogin ssoLogin = new SSOLogin("3cb09fe1253c4a3cbcbb0f65b3aead76", "http://localhost/");
                    ssoLogin.setRefreshToken("pXTqkJZK2EOpnFlxEN7h3A==");
                    try {
                        ssoLogin.fetchRefreshingCode();
                        GetStructureInfo obj = new GetStructureInfo(ssoLogin);
                        structure = obj.query(Long.valueOf(matcher.group(1))).name;
                    } catch (GetAccessTokenException | FetchProtectedResourceException | Non200CodeException e) {
                        System.out.println(e.getMessage());
                        structure = "??";
                    }
                } else {
                    structure = "??";
                    // @TODO: 日志
                }

                String shield;
                Pattern pShield = Pattern.compile("shieldPercentage:\\s(.*)");
                matcher = pShield.matcher(text);
                if (matcher.find()) {
                    Double shieldPercentage = Double.valueOf(matcher.group(1));
                    shield = String.format("%.0f", shieldPercentage);
                } else {
                    shield = "??";
                }

                String armor;
                Pattern pArmor = Pattern.compile("armorPercentage:\\s(.*)");
                matcher = pArmor.matcher(text);
                if (matcher.find()) {
                    Double armorPercentage = Double.valueOf(matcher.group(1));
                    armor = String.format("%.0f", armorPercentage);
                } else {
                    armor = "??";
                }

                String hull;
                Pattern pHull = Pattern.compile("hullPercentage:\\s(.*)");
                matcher = pHull.matcher(text);
                if (matcher.find()) {
                    Double hullPercentage = Double.valueOf(matcher.group(1));
                    hull = String.format("%.0f", hullPercentage);
                } else {
                    hull = "??";
                }

                String msg = String.format(
                        "【建筑警告】%s的%s正在被%s攻击\n受损建筑：%s\n攻击方：%s\n盾量/甲量/结构量：%s%%/%s%%/%s%%",
                        systemName, structureType, attacker, structure, attacker, shield, armor, hull);
                logger.info(msg);
                pusher.push(msg);
            });
        };
        // End Runnable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // noinspection InfiniteLoopStatement
        while (true) {
            logger.debug("Watch process start");
            Future<?> future = executorService.submit(runnable);
            try {
                future.get(5, TimeUnit.MINUTES);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println(e.toString());
            }
            logger.debug("Watch process stop");
            // 结束当前的监视周期，休眠程序
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(sleepInterval);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
