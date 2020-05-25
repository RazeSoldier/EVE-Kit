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

import jodd.mail.Email;
import jodd.mail.MailServer;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import org.jetbrains.annotations.NotNull;

public class MailAlarm implements Alarm {
    private final String recipient;
    private final SmtpServer smtpServer;
    private final String senderName;

    public MailAlarm(@NotNull String recipient, @NotNull String host, @NotNull String username, @NotNull String password,
                     @NotNull String senderName) {
        this.recipient = recipient;
        this.senderName = senderName;
        smtpServer = MailServer.create().host(host).auth(username, password).buildSmtpMailServer();
    }

    public void sendAlarm(@NotNull String watchSystemName, @NotNull String dstSystem, @NotNull Integer jumps) throws AlarmException {
        try {
            SendMailSession session = smtpServer.createSession();
            session.open();
            session.sendMail(buildMail(watchSystemName, dstSystem, jumps));
            session.close();
        } catch (Exception e) {
            throw new AlarmException(e);
        }
    }

    private Email buildMail(@NotNull String watchSystemName, @NotNull String dstSystem, @NotNull Integer jumps) {
        return Email.create()
                .from(senderName)
                .to(recipient)
                .subject(String.format("[Warning] Thera wormhole %d jumps from %s", jumps, watchSystemName))
                .textMessage(String.format("%s is %d jumps from %s", dstSystem, jumps, watchSystemName));
    }
}
