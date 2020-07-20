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

package razesoldier.thera.radar.model;

/**
 * Json model for config file
 */
public class ConfigModel {
    public String watchSystemName;
    public MailConfigModel mailConfig;
    public GameMailConfigModel gameMailConfig;
    public QQConfig qqConfig;

    public static class MailConfigModel {
        public String recipient;
        public String host;
        public String username;
        public String password;
        public String senderName;
    }

    public static class GameMailConfigModel {
        public Integer senderId;
        public String clientID;
        public String callbackURL;
        public String refreshToken;
    }

    public static class QQConfig {
        public String recipientGroup;
        public String coolqIP;
    }
}
