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

public class SystemInfoModel {
    private Integer constellation_id;
    private String name;
    private List<PlanetModel> planets = new ArrayList<>();
    private PositionModel position;
    private String security_class;
    private Double security_status;
    private Integer star_id;
    private List<Integer> stargates;
    private List<Integer> stations;
    private Integer system_id;

    public Integer getConstellation_id() {
        return constellation_id;
    }

    public void setConstellation_id(Integer constellation_id) {
        this.constellation_id = constellation_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlanetModel> getPlanets() {
        return planets;
    }

    public void setPlanets(List<PlanetModel> planets) {
        this.planets = planets;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }

    public String getSecurity_class() {
        return security_class;
    }

    public void setSecurity_class(String security_class) {
        this.security_class = security_class;
    }

    public Double getSecurity_status() {
        return security_status;
    }

    public void setSecurity_status(Double security_status) {
        this.security_status = security_status;
    }

    public Integer getStar_id() {
        return star_id;
    }

    public void setStar_id(Integer star_id) {
        this.star_id = star_id;
    }

    public List<Integer> getStargates() {
        return stargates;
    }

    public void setStargates(List<Integer> stargates) {
        this.stargates = stargates;
    }

    public List<Integer> getStations() {
        return stations;
    }

    public void setStations(List<Integer> stations) {
        this.stations = stations;
    }

    public Integer getSystem_id() {
        return system_id;
    }

    public void setSystem_id(Integer system_id) {
        this.system_id = system_id;
    }
}
