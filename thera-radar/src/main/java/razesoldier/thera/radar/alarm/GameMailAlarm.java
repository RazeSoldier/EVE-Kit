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
import razesoldier.esi.error.HttpRequestException;
import razesoldier.esi.error.InvalidStringException;
import razesoldier.esi.mail.Mail;
import razesoldier.esi.mail.Recipient;
import razesoldier.esi.mail.SendMail;
import razesoldier.esi.search.Search;
import razesoldier.esi.search.SearchType;
import razesoldier.esi.sso.FetchProtectedResourceException;
import razesoldier.esi.sso.GetAccessTokenException;
import razesoldier.esi.sso.SSOLogin;

public class GameMailAlarm implements Alarm {
    private final Integer senderId;
    private final SSOLogin login;

    public GameMailAlarm(@NotNull Integer senderId, @NotNull SSOLogin login) {
        this.senderId = senderId;
        this.login = login;
    }

    public void sendAlarm(@NotNull String watchSystemName, @NotNull String dstSystem, @NotNull Integer jumps)
            throws AlarmException {
        final String scope = "esi-mail.send_mail.v1";
        try {
            login.fetchRefreshingCode(scope);
        } catch (GetAccessTokenException e) {
            throw new AlarmException(e);
        }
        SendMail sender = new SendMail();
        Mail mail = new Mail();
        mail.setSenderId(senderId);
        mail.setSubject(String.format(
                "【席拉预警】一个席拉洞在%s %d跳外/[Thera WH Alarm] A Thera WH at %s",
                watchSystemName, jumps, dstSystem)
        );

        Search search = new Search();
        Integer id = null;
        try {
            id = (Integer) search.doSearch(new SearchType[]{SearchType.solarSystem}, dstSystem, true)
                    .getJSONArray("solar_system").get(0);
        } catch (HttpRequestException e) {
            throw new AlarmException(e);
        } catch (InvalidStringException ignored) {
        }
        dstSystem = String.format("<font size=\"12\" color=\"#ffd98d00\"><a href=\"showinfo:5//%d\">%s</a></font>", id, dstSystem);
        var body = "%s星系的席拉洞距离%s %d跳。\n\n此邮件通过ESI发送\n-------\n" +
                "A Thera wormhole at %s from %d jumps from 4LNE\n\nThis mail send by bot via ESI";
        mail.setBody(String.format(
                body, dstSystem, watchSystemName, jumps, dstSystem, jumps
        ));
        mail.addRecipient(new Recipient(Recipient.RecipientType.MailingList, 145218873));
        try {
            sender.send(mail, login);
        } catch (FetchProtectedResourceException e) {
            throw new AlarmException(e);
        }
    }
}
