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

package razesoldier.esi.error;

import org.jetbrains.annotations.NotNull;

public class Non200CodeException extends HttpRequestException {
    public Non200CodeException(@NotNull String url, int statusCode) {
        super(String.format("Request %s with %d error", url, statusCode));
    }

    public Non200CodeException(@NotNull String url, int statusCode, @NotNull String msg) {
        super(String.format("Request %s with %d error: %s", url, statusCode, msg));
    }
}
