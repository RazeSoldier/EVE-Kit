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

package razesoldier.esi.character;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum Gender {
    male(0), female(1);

    private int index;

    Gender(int index) {
        this.index = index;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        if (index == 0) {
            return "male";
        } else {
            return "female";
        }
    }

    public static Gender newByText(@NotNull String text) {
        if (text.equals("male")) {
            return Gender.male;
        }
        if (text.equals("female")) {
            return Gender.female;
        }
        throw new RuntimeException("Unsupported gender text: " + text);
    }
}
