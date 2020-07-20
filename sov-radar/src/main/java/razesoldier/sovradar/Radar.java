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

package razesoldier.sovradar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import razesoldier.esi.character.NotificationModel;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.universe.GetSystemInfo;
import razesoldier.sovradar.pusher.PushException;
import razesoldier.sovradar.pusher.Pusher;
import razesoldier.sovradar.pusher.PusherFactory;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 程序大部分生命周期在此。
 */
class Radar {
    private List<Watcher> watchers;
    // 监视的间隔，以毫秒为单位（默认1分钟）
    private Integer sleepInterval;

    Radar(@NotNull List<Watcher> watchers, @Nullable Integer sleepInterval) {
        this.watchers = watchers;
        this.sleepInterval = Objects.requireNonNullElse(sleepInterval, 1000 * 30);
    }

    /**
     * 主要的运行地方。此方法内部有无限循环。
     */
    void run() {
        Instant lastCatchTime = Instant.now(); // 上次监视到相关通知的时间戳
        //noinspection InfiniteLoopStatement
        while (true) {
            ExecutorService executors = Executors.newCachedThreadPool();
            List<Future<WatchReport>> futures = new ArrayList<>();
            final Instant finalLastWatchTime = lastCatchTime; // 用于传入lambda
            Instant currentTime = Instant.now(); // 在拉取ESI前记录当前的时间为这次的监视时间
            // Note：如果在记录当前时间和真正获取到ESI数据的期间刷新了晚于lastCatchTime的通知，会在下次监视周期里重新被监视到
            watchers.forEach(watcher -> {
                WatchTask task = new WatchTask(watcher, finalLastWatchTime);
                futures.add(executors.submit(task));
            });
            executors.shutdown();

            Set<NotificationModel> notificationModelSet = new HashSet<>();
            // 遍历所有的监视报告
            futures.forEach(watchReportFuture -> {
                WatchReport report;
                try {
                    report = watchReportFuture.get(30, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return;
                }
                notificationModelSet.addAll(report.getNewNotifications());
            });

            // 如果这次监视周期没有通知则立即结束当前的周期
            if (notificationModelSet.size() == 0) {
                sleep();
                continue;
            }

            lastCatchTime = currentTime; // 只有在当前监视周期监视到相关通知才会把lastCatchTime设置为当前监视周期的开始时间

            Pusher pusher = PusherFactory.newPusher();
            notificationModelSet.forEach(notificationModel -> {
                final String text = notificationModel.getText();
                // 匹配到星系ID @{
                Pattern r = Pattern.compile("solarSystemID: ([0-9]*)");
                Matcher m = r.matcher(text);
                if (!m.find()) {
                    return;
                }
                String systemId = m.group(1);
                // @}
                // 匹配主权设施的类型 @{
                r = Pattern.compile("structureTypeID: ([0-9]*)");
                m = r.matcher(text);
                if (!m.find()) {
                    return;
                }
                String type;
                switch (m.group(1)) {
                    case "32458":
                        type = "i-hub";
                        break;
                    case "32226":
                        type = "TCU";
                        break;
                    default:
                        throw new RuntimeException("Unsupported structureTypeID: " + m.group(1));
                }
                // @}
                String systemName;
                try {
                    systemName = new GetSystemInfo().query(Integer.valueOf(systemId)).getName();
                } catch (HttpRequestException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return;
                }

                // 推送消息
                String msg = String.format("【主权预警】%s星系的%s被傻逼照啦！！！！！赶紧TM去看看啊！！！！！", systemName, type);
                try {
                    pusher.push(msg);
                } catch (PushException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            });

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
