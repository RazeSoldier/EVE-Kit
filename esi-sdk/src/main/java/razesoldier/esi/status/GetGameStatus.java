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

import org.apache.commons.lang3.time.FastDateFormat;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.ConnectionException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.internal.InvalidStringException;
import razesoldier.esi.internal.StrMap2Map;
import razesoldier.esi.sso.ApiEntryPoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.TimeZone;

/**
 * API version: v1
 */
public class GetGameStatus {
    public static void main(String[] argv) throws ConnectionException, ParseException {
        GetGameStatus self = new GetGameStatus();
        System.out.println("Current players: " + self.getPlayerCount() + ", Startime: " + self.getStartTime());
    }

    private ApiEntryPoint entryPoint;

    public GetGameStatus() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    public Integer getPlayerCount() throws ConnectionException {
        return Integer.valueOf(getData().get("players"));
    }

    public String getServerVersion() throws ConnectionException {
        return getData().get("server_version");
    }

    public ZonedDateTime getStartTime() throws ConnectionException, ParseException {
        return FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC+0")).
                parse(getData().get("start_time")).toInstant().atZone(ZoneId.of("UTC+0"));
    }

    @NotNull
    private Map<String, String> getData() throws ConnectionException {
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create("https://esi.evetech.net/v1/status/?datasource=" + entryPoint)).build();
        HttpResponse<String> response;
        try {
            response = HttpClientFactory.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException(e);
        }
        try {
            return StrMap2Map.convert(response.body());
        } catch (InvalidStringException e) {
            throw new ConnectionException(e);
        }
    }
}
