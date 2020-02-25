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

public class WormholeModel {
    private Integer id;
    private Integer jumps;
    private SolarSystem destinationSolarSystem;

    public Integer getJumps() {
        return jumps;
    }

    public void setJumps(Integer jumps) {
        this.jumps = jumps;
    }

    public SolarSystem getDestinationSolarSystem() {
        return destinationSolarSystem;
    }

    public void setDestinationSolarSystem(SolarSystem destinationSolarSystem) {
        this.destinationSolarSystem = destinationSolarSystem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}