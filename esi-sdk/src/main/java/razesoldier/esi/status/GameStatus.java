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

package razesoldier.esi.status;

import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

public class GameStatus {
    private Integer players;
    private String serverVersion;
    private ZonedDateTime startTime;
    private Boolean vip;

    public Integer getPlayers() {
        return players;
    }

    void setPlayers(Integer players) {
        this.players = players;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public Boolean serverIsVip() {
        return vip;
    }

    void setVip(@Nullable Boolean vip) {
        this.vip = Objects.requireNonNullElse(vip, false);
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "players=" + players +
                ", serverVersion='" + serverVersion + '\'' +
                ", startTime=" + startTime +
                ", vip=" + vip +
                '}';
    }
}
