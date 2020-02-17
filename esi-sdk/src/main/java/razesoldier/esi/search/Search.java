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

package razesoldier.esi.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.ConnectionException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.sso.ApiEntryPoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Api version: v2
 */
public class Search {
    private ApiEntryPoint entryPoint;

    public Search() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    /**
     * @param entities Type of entities to search for
     * @param text The string to search on
     * @param strict Whether the search should be a strict match
     */
    public JSONObject doSearch(@NotNull SearchType[] entities, @NotNull String text, boolean strict) throws ConnectionException {
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(generateUrl(entities, text, strict))).build();
        HttpResponse<String> response;
        try {
            response = HttpClientFactory.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException(e);
        }
        return JSON.parseObject(response.body());
    }

    @NotNull
    private String generateUrl(@NotNull SearchType[] entities, @NotNull String text, boolean strict) {
        return "https://esi.evetech.net/v2/search/?" +
                "categories=" + StringUtils.join(entities, ",") +
                "&datasource=" + entryPoint +
                "&search=" + text +
                "&strict=" + strict;
    }
}
