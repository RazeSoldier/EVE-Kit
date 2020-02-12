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

package razesoldier.esi.internal;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * This class is used to convert a map represented by a string into a {@link java.util.Map} in Java.
 * <pre>
 * A map represented by a string like:
 * {"players":11579,"server_version":"1648688","start_time":"2020-01-18T11:02:36Z"}
 * </pre>
 */
public class StrMap2Map {
    @NotNull
    public static Map<String, String> convert(@NotNull String arr) throws InvalidStringException {
        if (!arr.startsWith("{")) {
            throw new InvalidStringException("0th position of the string is illegal");
        }
        if (!arr.endsWith("}")) {
            throw new InvalidStringException("end position of the string is illegal");
        }

        arr = arr.substring(1, arr.length() - 1);
        String[] items = arr.split(",");
        Map<String, String> res = new HashMap<>();
        for (String s : items) {
            String[] substring = s.split(":", 2);
            String key = substring[0].substring(1, substring[0].length() - 1);
            String value;
            if (substring[1].startsWith("\"")) {
                value = substring[1].substring(1, substring[1].length() - 1);
            } else {
                value = substring[1];
            }
            res.put(key, value);
        }
        return res;
    }
}
