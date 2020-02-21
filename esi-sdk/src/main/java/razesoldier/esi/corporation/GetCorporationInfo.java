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
import razesoldier.esi.error.Non200CodeException;
import razesoldier.esi.internal.HttpClientFactory;
import razesoldier.esi.sso.ApiEntryPoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
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
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClientFactory.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HttpRequestException(e);
        }
        if (response.statusCode() != 200) {
            throw new Non200CodeException(url, response.statusCode(), response.body());
        }
        CorporationInfoModel model = JSON.parseObject(response.body(), CorporationInfoModel.class);
        CorporationInfo info = new CorporationInfo();
        info.setAllianceId(model.getAlliance_id());
        info.setCeoId(model.getCeo_id());
        info.setCreatorId(model.getCreator_id());
        if (model.getDate_founded() != null) {
            info.setDateFounded(FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC+0")).
                    parse(model.getDate_founded()).toInstant().atZone(ZoneId.of("UTC+0")));
        }
        info.setDescription(model.getDescription());
        info.setHomeStationId(model.getHome_station_id());
        info.setMemberCount(model.getMember_count());
        info.setName(model.getName());
        info.setShares(model.getShares());
        info.setTaxRate(model.getTax_rate());
        info.setTicker(model.getTicker());
        info.setUrl(model.getUrl());
        info.setWarEligible(model.getWar_eligible());
        return info;
    }
}
