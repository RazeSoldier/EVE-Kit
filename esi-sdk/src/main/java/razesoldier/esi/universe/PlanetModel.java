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

package razesoldier.esi.universe;

import java.util.ArrayList;
import java.util.List;

public class PlanetModel {
    private List<Integer> asteroid_belts = new ArrayList<>();
    private List<Integer> moons = new ArrayList<>();
    private Integer planet_id;

    public List<Integer> getAsteroid_belts() {
        return asteroid_belts;
    }

    public void setAsteroid_belts(List<Integer> asteroid_belts) {
        this.asteroid_belts = asteroid_belts;
    }

    public List<Integer> getMoons() {
        return moons;
    }

    public void setMoons(List<Integer> moons) {
        this.moons = moons;
    }

    public Integer getPlanet_id() {
        return planet_id;
    }

    public void setPlanet_id(Integer planet_id) {
        this.planet_id = planet_id;
    }
}
