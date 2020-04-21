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

package razesoldier.thera.radar.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

class DefaultApi implements Api {
    public String query(@NotNull String systemSearch) throws ConnectionException {
        final String apiEndpoint = "https://www.eve-scout.com/api/wormholes?systemSearch=%s";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format(apiEndpoint, systemSearch)))
                .timeout(Duration.ofMinutes(1)).build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(1)).build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException(e);
        }
        if (response.statusCode() != 200) {
            throw new ConnectionException(String.format("Http status code %d: %s", response.statusCode(), response.body()));
        }
        return response.body();
    }
}
