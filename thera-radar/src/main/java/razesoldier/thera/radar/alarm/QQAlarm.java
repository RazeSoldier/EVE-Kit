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

package razesoldier.thera.radar.alarm;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class QQAlarm implements Alarm {
    private final String coolqIP;
    private final String recipientGroup;

    public QQAlarm(@NotNull String coolqIP, @NotNull String recipientGroup) {
        this.coolqIP = coolqIP;
        this.recipientGroup = recipientGroup;
    }

    public void sendAlarm(@NotNull String watchSystemName, @NotNull String dstSystem, @NotNull Integer jumps) throws AlarmException {
        String msg = String.format(
                "【席拉预警】一个席拉洞在%s %d跳外\n%s星系的席拉洞距离%s %d跳",
                watchSystemName, jumps, dstSystem, watchSystemName, jumps
        );

        String url = String.format(
                "http://%s/send_group_msg?group_id=%s&message=%s",
                coolqIP, recipientGroup, URLEncoder.encode(msg, StandardCharsets.UTF_8)
        );
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new AlarmException(e);
        }
    }
}
