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

package razesoldier.esi.internal;

import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.error.Non200CodeException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientFactory {
    private static HttpClientFactory instance;
    private HttpClient httpClient;

    private HttpClientFactory() {
        httpClient = HttpClient.newBuilder().build();
    }

    synchronized public static HttpClientFactory getInstance() {
        if (instance == null) {
            instance = new HttpClientFactory();
        }
        return instance;
    }

    @NotNull
    public HttpClient getHttpClient() {
        return httpClient;
    }

    public static HttpRequest newRequest(@NotNull String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).build();
    }

    @NotNull
    public static HttpResponse<String> quickRequest(@NotNull String url) throws HttpRequestException {
        HttpRequest request = newRequest(url);
        HttpResponse<String> response;
        try {
            response = HttpClientFactory.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HttpRequestException(e);
        }
        if (response.statusCode() != 200) {
            throw new Non200CodeException(url, response.statusCode(), response.body());
        }
        return response;
    }
}
