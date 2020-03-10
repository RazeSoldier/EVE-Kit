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

package razesoldier.esi.character;

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
 * Get public information about a character
 * Api version: v4
 */
public class GetCharacterInfo {
    private ApiEntryPoint entryPoint;

    public GetCharacterInfo() {
        entryPoint = ApiEntryPoint.Tranquility;
    }

    @NotNull
    public CharacterInfo getInfo(@NotNull Integer id) throws HttpRequestException, ParseException {
        final String endpoint = "https://esi.evetech.net/v4/characters/%d/?datasource=" + entryPoint;
        final String url = String.format(endpoint, id);
        HttpResponse<String> response = HttpClientFactory.quickRequest(url);

        CharacterInfoModel model = JSON.parseObject(response.body(), CharacterInfoModel.class);
        CharacterInfo info = new CharacterInfo();
        info.setAllianceId(model.getAlliance_id());
        info.setAncestryId(model.getAncestry_id());
        info.setBirthday(FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC+0")).
                parse(model.getBirthday()).toInstant().atZone(ZoneId.of("UTC+0")));
        info.setBloodlineId(model.getBloodline_id());
        info.setCorporationId(model.getCorporation_id());
        info.setDescription(model.getDescription());
        info.setGender(Gender.newByText(model.getGender()));
        info.setName(model.getName());
        info.setRaceId(model.getRace_id());
        info.setSecurityStatus(model.getSecurity_status());
        info.setTitle(model.getTitle());
        return info;
    }
}
