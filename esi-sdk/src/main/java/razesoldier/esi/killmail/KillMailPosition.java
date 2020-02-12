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

package razesoldier.esi.killmail;

public class KillMailPosition {
    private Double x;
    private Double y;
    private Double z;

    KillMailPosition() {}

    public Double getX() {
        return x;
    }

    void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    void setZ(Double z) {
        this.z = z;
    }
}
