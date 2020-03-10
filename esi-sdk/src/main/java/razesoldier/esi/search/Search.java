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
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.error.InvalidStringException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.sso.ApiEntryPoint;

import java.net.http.HttpResponse;

/**
 * Search for entities that match a given sub-string.
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
     * @throws InvalidStringException Throw an exception when the length of the search string less than 2
     */
    public JSONObject doSearch(@NotNull SearchType[] entities, @NotNull String text, boolean strict)
            throws HttpRequestException, InvalidStringException {
        // The length of the search string must be greater than 2 due to the limitation of ESI
        if (text.length() < 3) {
            throw new InvalidStringException("The length of the search string must be greater than 2");
        }

        final String url = generateUrl(entities, text, strict);
        HttpResponse<String> response = HttpClientFactory.quickRequest(url);
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
