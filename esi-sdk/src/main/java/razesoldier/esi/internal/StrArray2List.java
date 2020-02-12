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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to convert an array represented by a string into a {@link java.util.List} in Java.
 * <pre>
 * An array represented by a string like:
 * [1245124,6901i34,6]
 * </pre>
 */
public class StrArray2List {
    @NotNull
    public static List<String> convert(@NotNull String arr) throws InvalidStringException {
        if (!arr.startsWith("[")) {
            throw new InvalidStringException("0th position of the string is illegal");
        }
        if (!arr.endsWith("]")) {
            throw new InvalidStringException("end position of the string is illegal");
        }

        arr = arr.substring(1, arr.length() - 1);
        return new ArrayList<>(Arrays.asList(arr.split(",")));
    }
}
