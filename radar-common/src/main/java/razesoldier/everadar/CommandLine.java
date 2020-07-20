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

package razesoldier.everadar;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandLine {
    private CommandLine() {
    }

    private final Map<String, String> options = new HashMap<>();
    private final List<String> params = new ArrayList<>();

    public static @NotNull CommandLine parse(String[] args) {
        CommandLine commandLine = new CommandLine();
        var context = new Object() {
            boolean beforeIsKey;
            String key;
        };
        Arrays.asList(args).forEach(s -> {
            if (s.startsWith("--")) {
                if (context.beforeIsKey) {
                    // like: java Main --debug --log
                    commandLine.addOption(context.key, "true"); // Save the previous key as a pure key
                    context.key = s.substring(2);
                } else {
                    // like: java Main --log
                    context.beforeIsKey = true;
                    context.key = s.substring(2);
                }
            } else {
                if (context.beforeIsKey) {
                    // like: java Main --log 1.txt
                    commandLine.addOption(context.key, s);
                    context.beforeIsKey = false;
                    context.key = null;
                } else {
                    // like: java Main start
                    commandLine.addParam(s);
                }
            }
        });
        if (args[args.length - 1].startsWith("--")) {
            // like: java Main --log 1.txt --debug
            commandLine.addOption(args[args.length - 1].substring(2), "true");
        }
        return commandLine;
    }

    public String getOption(@NotNull String key) {
        return options.get(key);
    }

    public boolean hasParam(@NotNull String key) {
        return params.contains(key);
    }

    private void addOption(@NotNull String key, String value) {
        options.put(key, value);
    }

    private void addParam(@NotNull String param) {
        params.add(param);
    }
}
