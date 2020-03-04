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

package razesoldier.esi.universe;

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.error.Non200CodeException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.sso.ApiEntryPoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Get information on a type
 * Api version: v3
 */
public class GetItemTypeInfo {
    private ApiEntryPoint entryPoint;

    public GetItemTypeInfo() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    public ItemTypeInfoModel query(@NotNull Integer id) throws HttpRequestException {
        final String url = String.format("https://esi.evetech.net/v3/universe/types/%d/?datasource=%s&language=zh", id, entryPoint);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response;
        try {
            response = HttpClientFactory.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HttpRequestException(e);
        }
        if (response.statusCode() != 200) {
            throw new Non200CodeException(url, response.statusCode(), response.body());
        }
        return JSON.parseObject(response.body(), ItemTypeInfoModel.class);
    }
}
