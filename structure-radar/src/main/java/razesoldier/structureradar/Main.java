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

package razesoldier.structureradar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import razesoldier.everadar.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * 程序入口点
 */
public class Main {
    public static void main(String[] argv) {
        var commandLine = CommandLine.parse(argv);
        final String configPath;
        if (commandLine.getOption("configPath") == null) {
            exit("Missing required option '--configPath'");
            return;
        }
        configPath = commandLine.getOption("configPath");
        File configFile = new File(configPath);
        if (!configFile.canRead()) {
            exit(String.format("Failed to read %s", configFile));
            return;
        }

        final String configText;
        try {
            configText = Files.readString(configFile.toPath());
        } catch (IOException e) {
            exit(e.getMessage());
            return;
        }

        ConfigModel configModel;
        try {
            configModel = JSON.parseObject(configText, ConfigModel.class);
        } catch (JSONException e) {
            exit(configPath + " is invalid json file");
            return;
        }
        List<WatcherModel> watcherModelList = configModel.watchers;
        if (watcherModelList == null) {
            exit("No watcher in config file");
            return;
        }

        var debugLevel = commandLine.getOption("debug") != null ? "debug" : "info";
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", debugLevel);
        System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
        Logger logger = LoggerFactory.getLogger("structure-radar");
        // Boot finish

        // Build Watcher
        List<Watcher> watchers = new ArrayList<>();
        watcherModelList.forEach(watcherModel -> {
            var watcher = Watcher.newInstance(watcherModel.name, watcherModel.uid, watcherModel.refreshToken);
            // 检查监视者的刷新令牌是否有效
            if (watcher.checkRefreshToken()) {
                watchers.add(watcher);
            } else {
                logger.warn("[Invalid Watcher] " + watcher.getName());
            }
        });

        logger.info("Started structure-radar");
        Radar radar = new Radar(watchers, configModel.sleepInterval, logger);
        radar.run();
    }

    private static void exit(@NotNull String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
