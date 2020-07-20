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
import razesoldier.esi.character.NotificationModel;

import java.util.List;

/**
 * 代表监视的报告。
 */
class WatchReport {
    // 是否有新的情况
    private boolean isDetected;
    // 新的通知
    private List<NotificationModel> newNotifications;

    WatchReport(@NotNull List<NotificationModel> newNotifications) {
        this.newNotifications = newNotifications;
        isDetected = newNotifications.size() != 0;
    }

    /**
     * 是否监视到特定的行为？
     */
    boolean isDetected() {
        return isDetected;
    }

    @NotNull
    List<NotificationModel> getNewNotifications() {
        return newNotifications;
    }
}
