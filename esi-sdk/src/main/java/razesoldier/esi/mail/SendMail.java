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

package razesoldier.esi.mail;

import org.jetbrains.annotations.NotNull;
import razesoldier.esi.sso.ApiEntryPoint;
import razesoldier.esi.sso.FetchProtectedResourceException;
import razesoldier.esi.sso.GetAccessTokenException;
import razesoldier.esi.sso.SSOLogin;

import java.util.HashMap;
import java.util.Map;

/**
 * Api version: v1
 * Required scope: esi-mail.send_mail.v1
 */
public class SendMail {
    private ApiEntryPoint entryPoint;

    public SendMail() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    public Map<String, String> send(@NotNull Mail mail, @NotNull SSOLogin loginService) throws FetchProtectedResourceException {
        final String endpoint = "https://esi.evetech.net/v1/characters/%d/mail/?datasource=" + entryPoint;
        Map<String, String> resp = loginService.postProtectedResource(String.format(endpoint, mail.getSenderId()), mail.toJson());
        Map<String, String> map = new HashMap<>();
        map.put("code", resp.get("code"));
        map.put("body", resp.get("body"));
        return map;
    }
}
