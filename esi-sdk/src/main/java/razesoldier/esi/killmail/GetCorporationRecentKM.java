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

package razesoldier.esi.killmail;

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.sso.FetchProtectedResourceException;
import razesoldier.esi.sso.GetAccessTokenException;
import razesoldier.esi.sso.SSOLogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetCorporationRecentKM {
    final private String cid;
    final private SSOLogin loginService;

    public GetCorporationRecentKM(@NotNull String cid, @NotNull SSOLogin loginService) {
        this.cid = cid;
        this.loginService = loginService;
    }

    public List<KillMailTitle> fetchKillMail() throws FetchProtectedResourceException {
        return fetchKillMail(1);
    }

    @NotNull
    public List<KillMailTitle> fetchKillMail(int page) throws FetchProtectedResourceException {
        final String endpoint = "https://esi.evetech.net/v1/corporations/%s/killmails/recent/?datasource=tranquility&page=%d";
        Map<String, String> resp = loginService.fetchProtectedResource(String.format(endpoint, cid, page));
        if (!resp.get("code").equals("200")) {
            throw new FetchProtectedResourceException("Code: " + resp.get("code") + ", message: " + resp.get("body"));
        }
        String body = resp.get("body");
        List<KillMailTitleModel> kmms = JSON.parseArray(body, KillMailTitleModel.class);
        List<KillMailTitle> kms = new ArrayList<>();
        kmms.forEach(killMail -> {
            KillMailTitle km = new KillMailTitle();
            km.setHash(killMail.getHash());
            km.setId(killMail.getId());
            kms.add(km);
        });
        return kms;
    }
}
