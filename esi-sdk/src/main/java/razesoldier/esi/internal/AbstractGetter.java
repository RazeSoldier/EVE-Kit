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

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.HttpRequestException;

import java.net.http.HttpResponse;

abstract public class AbstractGetter {
    protected <T> T requestWithoutAuth(@NotNull String url, @NotNull Class<T> model) throws HttpRequestException {
        HttpResponse<String> response = HttpClientFactory.quickRequest(url);
        return JSON.parseObject(response.body(), model);
    }
}
