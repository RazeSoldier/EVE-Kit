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

/**
 * 配置文件关于监视者的JSON模型
 */
class WatcherModel {
    // 监视者的昵称，随便起，用于区别其它监视者
    public String name;
    // 用于登录ESI的刷新令牌（不能泄露此令牌给外部）
    // 此刷新令牌应该可以访问esi-characters.read_notifications.v1权限域
    public String refreshToken;
    // 监视器的用户ID
    public Integer uid;

    @Override
    public String toString() {
        return "WatcherModel{" +
                "name='" + name + '\'' +
                ", uid=" + uid +
                '}';
    }
}
