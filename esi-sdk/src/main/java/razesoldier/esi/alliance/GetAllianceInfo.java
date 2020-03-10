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

package razesoldier.esi.alliance;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.FastDateFormat;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.sso.ApiEntryPoint;

import java.net.http.HttpResponse;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Get a public information about an alliance
 * Api version: v3
 */
public class GetAllianceInfo {
    private ApiEntryPoint apiEntryPoint;

    public GetAllianceInfo() {
        apiEntryPoint = ApiEntryPoint.Tranquility;
    }

    public AllianceInfo query(@NotNull Integer id) throws HttpRequestException, ParseException {
        final String endpoint = "https://esi.evetech.net/v3/alliances/%d/?datasource=%s";
        final String url = String.format(endpoint, id, apiEntryPoint);
        HttpResponse<String> response = HttpClientFactory.quickRequest(url);

        AllianceInfoModel model = JSON.parseObject(response.body(), AllianceInfoModel.class);
        AllianceInfo info = new AllianceInfo();
        info.setCreatorCorporationId(model.getCreator_corporation_id());
        info.setCreatorId(model.getCreator_id());
        info.setDateFounded(FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC+0")).
                parse(model.getDate_founded()).toInstant().atZone(ZoneId.of("UTC+0")));
        info.setExecutorCorporationId(model.getExecutor_corporation_id());
        info.setName(model.getName());
        info.setTicker(model.getTicker());
        return info;
    }
}
