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

import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.error.InvalidStringException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.internal.StrArray2List;
import razesoldier.esi.sso.ApiEntryPoint;

import java.net.http.HttpResponse;
import java.util.List;

/**
 * List all alliances
 * API version: v1
 */
public class ListAllAlliances {
    private ApiEntryPoint entryPoint;

    public ListAllAlliances() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    public List<String> getAllAllianceList() throws HttpRequestException {
        final String url = String.format("https://esi.evetech.net/v1/alliances/?datasource=%s", entryPoint);
        HttpResponse<String> response = HttpClientFactory.quickRequest(url);

        try {
            return StrArray2List.convert(response.body());
        } catch (InvalidStringException e) {
            throw new HttpRequestException(e);
        }
    }
}
