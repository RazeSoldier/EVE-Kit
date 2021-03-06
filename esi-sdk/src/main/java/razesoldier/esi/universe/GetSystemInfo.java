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

import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.internal.AbstractGetter;
import razesoldier.esi.sso.ApiEntryPoint;


/**
 * Get information on a solar system.
 * Api version: v4
 */
public class GetSystemInfo extends AbstractGetter {
    private ApiEntryPoint entryPoint;

    public GetSystemInfo() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    public SystemInfoModel query(@NotNull Integer id) throws HttpRequestException {
        final String endpoint = "https://esi.evetech.net/v4/universe/systems/%d/?datasource=%s";
        final String url = String.format(endpoint, id, entryPoint);

        return requestWithoutAuth(url, SystemInfoModel.class);
    }
}
