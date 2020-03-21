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

package razesoldier.esi.corporation;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.FastDateFormat;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.model.CorporationInfoModel;
import razesoldier.esi.sso.ApiEntryPoint;

import java.net.http.HttpResponse;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Get a public information about a corporation
 */
public class GetCorporationInfo {
    private ApiEntryPoint entryPoint;

    public GetCorporationInfo() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    public CorporationInfo query(@NotNull Integer id) throws HttpRequestException, ParseException {
        final String endpoint = "https://esi.evetech.net/v4/corporations/%d/?datasource=%s";
        final String url = String.format(endpoint, id, entryPoint);
        HttpResponse<String> response = HttpClientFactory.quickRequest(url);

        CorporationInfoModel model = JSON.parseObject(response.body(), CorporationInfoModel.class);
        CorporationInfo info = new CorporationInfo();
        info.setAllianceId(model.alliance_id);
        info.setCeoId(model.ceo_id);
        info.setCreatorId(model.creator_id);
        if (model.date_founded != null) {
            info.setDateFounded(FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC+0")).
                    parse(model.date_founded).toInstant().atZone(ZoneId.of("UTC+0")));
        }
        info.setDescription(model.description);
        info.setHomeStationId(model.home_station_id);
        info.setMemberCount(model.member_count);
        info.setName(model.name);
        info.setShares(model.shares);
        info.setTaxRate(model.tax_rate);
        info.setTicker(model.ticker);
        info.setUrl(model.url);
        info.setWarEligible(model.war_eligible);
        return info;
    }
}
