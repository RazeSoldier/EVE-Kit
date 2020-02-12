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

public class AttackerModel {
    private Integer alliance_id;
    private Integer character_id;
    private Integer corporation_id;
    private Integer damage_done;
    private Integer faction_id;
    private boolean final_blow;
    private Float security_status;
    private Integer ship_type_id;
    private Integer weapon_type_id;

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

    public Integer getDamage_done() {
        return damage_done;
    }

    public void setDamage_done(Integer damage_done) {
        this.damage_done = damage_done;
    }

    public Integer getFaction_id() {
        return faction_id;
    }

    public void setFaction_id(Integer faction_id) {
        this.faction_id = faction_id;
    }

    public boolean isFinal_blow() {
        return final_blow;
    }

    public void setFinal_blow(boolean final_blow) {
        this.final_blow = final_blow;
    }

    public Float getSecurity_status() {
        return security_status;
    }

    public void setSecurity_status(Float security_status) {
        this.security_status = security_status;
    }

    public Integer getShip_type_id() {
        return ship_type_id;
    }

    public void setShip_type_id(Integer ship_type_id) {
        this.ship_type_id = ship_type_id;
    }

    public Integer getWeapon_type_id() {
        return weapon_type_id;
    }

    public void setWeapon_type_id(Integer weapon_type_id) {
        this.weapon_type_id = weapon_type_id;
    }
}
