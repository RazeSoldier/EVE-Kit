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

package razesoldier.thera.radar;

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.sso.SSOLogin;
import razesoldier.thera.radar.alarm.GameMailAlarm;
import razesoldier.thera.radar.alarm.MailAlarm;
import razesoldier.thera.radar.alarm.QQAlarm;
import razesoldier.thera.radar.api.ConnectionException;
import razesoldier.thera.radar.model.ConfigModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Daemon {
    public static void main(String[] argv) {
        Map<String, String> argvMap;
        try {
            argvMap = parseArgv(argv);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return;
        }
        final String configPath = argvMap.get("configPath");
        File configFile = new File(configPath);
        if (!configFile.canRead()) {
            System.out.println(String.format("Failed to read %s", configFile));
            System.exit(1);
            return;
        }

        final String configText;
        try {
            configText = Files.readString(configFile.toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return;
        }

        ConfigModel configModel = JSON.parseObject(configText, ConfigModel.class);
        ConfigModel.MailConfigModel mailConfig = configModel.mailConfig;
        ConfigModel.GameMailConfigModel gameMailConfig = configModel.gameMailConfig;
        ConfigModel.QQConfig qqConfig = configModel.qqConfig;

        final String watchSystemName = configModel.watchSystemName;
        final String scope = "esi-mail.send_mail.v1";
        Radar radar = new Radar(watchSystemName);
        if (mailConfig != null) {
            radar.addAlarm(
                    new MailAlarm(mailConfig.recipient, mailConfig.host, mailConfig.username, mailConfig.password, mailConfig.senderName)
            );
        }
        if (gameMailConfig != null) {
            SSOLogin login = new SSOLogin(gameMailConfig.clientID, gameMailConfig.callbackURL, scope)
                    .setRefreshToken(gameMailConfig.refreshToken);
            radar.addAlarm(new GameMailAlarm(gameMailConfig.senderId, login));
        }
        if (qqConfig != null) {
            radar.addAlarm(new QQAlarm(qqConfig.recipientGroup));
        }

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                radar.echo();
            } catch (ConnectionException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000 * 120); // Sleep 2 minutes
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析命令行参数
     */
    @NotNull
    private static Map<String, String> parseArgv(@NotNull String[] argv) throws Exception {
        boolean hasConfig = false;
        String configFilePath = null;
        // 必须指定一个配置文件（--config-path）
        for (String value : argv) {
            if (hasConfig) {
                configFilePath = value;
            }
            if (value.indexOf("--config-path") != 0) {
                continue;
            }
            hasConfig = true;
        }
        if (!hasConfig) {
            throw new Exception("--config-path must be specified");
        }
        if (configFilePath == null) {
            throw new Exception("--config-path must be specified a value");
        }
        Map<String, String> argvMap = new HashMap<>();
        argvMap.put("configPath", configFilePath);
        return argvMap;
    }
}
