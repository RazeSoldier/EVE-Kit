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
import razesoldier.esi.error.Non200CodeException;
import razesoldier.esi.sso.ApiEntryPoint;
import razesoldier.esi.sso.FetchProtectedResourceException;
import razesoldier.esi.sso.SSOLogin;

import java.util.Map;

/**
 * Returns information on requested structure if you are on the ACL. Otherwise, returns “Forbidden” for all inputs.
 * Api version: v2
 */
public class GetStructureInfo {
    private ApiEntryPoint entryPoint;
    private SSOLogin login;

    public GetStructureInfo(@NotNull SSOLogin login) {
        entryPoint = ApiEntryPoint.Tranquility;
        this.login = login;
    }

    public StructureInfoModel query(@NotNull Long id) throws FetchProtectedResourceException, Non200CodeException {
        final String url = String.format("https://esi.evetech.net/v2/universe/structures/%d/?datasource=%s", id, entryPoint);
        Map<String, String> resp = login.fetchProtectedResource(url);
        if (!resp.get("code").equals("200")) {
            throw new Non200CodeException(url, Integer.parseInt(resp.get("code")), resp.get("bdoy"));
        }
        return JSON.parseObject(resp.get("body"), StructureInfoModel.class);
    }
}
