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

package razesoldier.esi.sso;

/**
 * Note: ESI currently does not support servers other than production server (Tranquility)
 */
public enum ApiEntryPoint {
    Tranquility(0); // production server

    private int index;

    ApiEntryPoint(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        if (index == 0) {
            return "tranquility";
        }
        return null;
    }
}
