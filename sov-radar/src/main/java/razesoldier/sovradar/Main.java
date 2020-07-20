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

package razesoldier.sovradar;

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 程序入口点
 */
public class Main {
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
        List<WatcherModel> watcherModelList = configModel.getWatchers();
        if (watcherModelList.size() == 0) {
            System.out.println("No watchers in configuration");
            System.exit(1);
            return;
        }

        List<Watcher> watchers = new ArrayList<>();
        watcherModelList.forEach(watcherModel ->
                watchers.add(Watcher.newInstance(watcherModel.getName(), watcherModel.getUid(), watcherModel.getRefreshToken()))
        );

        Radar radar = new Radar(watchers, configModel.getSleepInterval());
        radar.run();
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
