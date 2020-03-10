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

import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.internal.AbstractGetter;
import razesoldier.esi.model.GameStatusModel;
import razesoldier.esi.sso.ApiEntryPoint;

import java.time.ZonedDateTime;

/**
 * API version: v1
 */
public class GetGameStatus extends AbstractGetter {
    private ApiEntryPoint entryPoint;

    public GetGameStatus() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    public Integer getPlayerCount() throws HttpRequestException {
        return getStatus().getPlayers();
    }

    public String getServerVersion() throws HttpRequestException {
        return getStatus().getServerVersion();
    }

    public ZonedDateTime getStartTime() throws HttpRequestException {
        return getStatus().getStartTime();
    }

    @NotNull
    public Boolean serverIsVip() throws HttpRequestException {
        return getStatus().serverIsVip();
    }

    @NotNull
    public GameStatus getStatus() throws HttpRequestException {
        final String url = "https://esi.evetech.net/v1/status/?datasource=" + entryPoint;

        GameStatusModel model = requestWithoutAuth(url, GameStatusModel.class);
        GameStatus gameStatus = new GameStatus();
        gameStatus.setPlayers(model.players);
        gameStatus.setServerVersion(model.server_version);
        gameStatus.setStartTime(ZonedDateTime.parse(model.start_time));
        gameStatus.setVip(model.vip);
        return gameStatus;
    }
}
