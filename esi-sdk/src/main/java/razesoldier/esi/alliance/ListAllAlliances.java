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

package razesoldier.esi.alliance;

import razesoldier.esi.error.ConnectionException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.internal.InvalidStringException;
import razesoldier.esi.internal.StrArray2List;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * List all alliances
 * API version: v1
 */
public class ListAllAlliances {
    public List<String> getAllAllianceList() throws ConnectionException {
        URI url = URI.create("https://esi.evetech.net/v1/alliances/?datasource=tranquility");
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();
        HttpResponse<String> response;
        try {
            response = HttpClientFactory.getInstance().getHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException(e);
        }
        try {
            return StrArray2List.convert(response.body());
        } catch (InvalidStringException e) {
            throw new ConnectionException(e);
        }
    }
}
