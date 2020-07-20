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
import razesoldier.esi.character.GetNotifications;
import razesoldier.esi.character.NotificationModel;
import razesoldier.esi.sso.SSOLogin;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class WatchTask implements Callable<WatchReport> {
    // 执行这个监视任务的监视者
    private Watcher watcher;
    // 上次监视的unix时间戳
    private Instant lastTime;

    WatchTask(@NotNull Watcher watcher, @NotNull Instant lastTime) {
        this.watcher = watcher;
        this.lastTime = lastTime;
    }

    /**
     * 返回监视报告
     */
    @NotNull
    @Override
    public WatchReport call() throws Exception {
        // 调用ESI
        SSOLogin loginService = watcher.getLoginService().fetchRefreshingCode();
        GetNotifications getter = new GetNotifications(loginService);
        List<NotificationModel> modelList = getter.query(watcher.getUid());
        List<NotificationModel> newNotifications = new ArrayList<>();
        // 遍历通知列表
        modelList.forEach(notificationModel -> {
            // 首先过滤掉旧的通知
            // 如果通知时间晚于上次的监视时间，则忽略此通知
            Instant time = ZonedDateTime.parse(notificationModel.getTimestamp()).toInstant();
            if (time.isBefore(lastTime)) {
                return;
            }
            // 过滤掉除EntosisCaptureStarted类型以外的通知
            if (!notificationModel.getType().equals("StructureUnderAttack")) {
                return;
            }
            // 经过过滤，现在剩下来的通知属于新的主权被攻击的通知
            newNotifications.add(notificationModel);
        });
        return new WatchReport(newNotifications);
    }
}
