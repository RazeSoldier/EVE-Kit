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

import java.util.ArrayList;
import java.util.List;

public class VictimModel {
    private Integer alliance_id;
    private Integer character_id;
    private Integer corporation_id;
    private Integer damage_taken;
    private Integer faction_id;
    private List<KillMailItemModel> items = new ArrayList<>();
    private KillMailPositionModel position;
    private Integer ship_type_id;

    public Integer getAlliance_id() {
        return alliance_id;
    }

    public void setAlliance_id(Integer alliance_id) {
        this.alliance_id = alliance_id;
    }

    public Integer getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(Integer character_id) {
        this.character_id = character_id;
    }

    public Integer getCorporation_id() {
        return corporation_id;
    }

    public void setCorporation_id(Integer corporation_id) {
        this.corporation_id = corporation_id;
    }

    public Integer getDamage_taken() {
        return damage_taken;
    }

    public void setDamage_taken(Integer damage_taken) {
        this.damage_taken = damage_taken;
    }

    public Integer getFaction_id() {
        return faction_id;
    }

    public void setFaction_id(Integer faction_id) {
        this.faction_id = faction_id;
    }

    public List<KillMailItemModel> getItems() {
        return items;
    }

    public void setItems(List<KillMailItemModel> items) {
        this.items = items;
    }

    public KillMailPositionModel getPosition() {
        return position;
    }

    public void setPosition(KillMailPositionModel position) {
        this.position = position;
    }

    public Integer getShip_type_id() {
        return ship_type_id;
    }

    public void setShip_type_id(Integer ship_type_id) {
        this.ship_type_id = ship_type_id;
    }
}
