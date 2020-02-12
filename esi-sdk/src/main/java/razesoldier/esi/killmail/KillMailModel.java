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

public class KillMailModel {
    private List<AttackerModel> attackers = new ArrayList<>();
    private Integer killmail_id;
    private String killmail_time;
    private Integer moon_id;
    // Solar system that the kill took place in
    private Integer solar_system_id;
    private VictimModel victim;
    private Integer war_id;

    public List<AttackerModel> getAttackers() {
        return attackers;
    }

    public void setAttackers(List<AttackerModel> attackers) {
        this.attackers = attackers;
    }

    public Integer getKillmail_id() {
        return killmail_id;
    }

    public void setKillmail_id(Integer killmail_id) {
        this.killmail_id = killmail_id;
    }

    public String getKillmail_time() {
        return killmail_time;
    }

    public void setKillmail_time(String killmail_time) {
        this.killmail_time = killmail_time;
    }

    public Integer getMoon_id() {
        return moon_id;
    }

    public void setMoon_id(Integer moon_id) {
        this.moon_id = moon_id;
    }

    public Integer getSolar_system_id() {
        return solar_system_id;
    }

    public void setSolar_system_id(Integer solar_system_id) {
        this.solar_system_id = solar_system_id;
    }

    public VictimModel getVictim() {
        return victim;
    }

    public void setVictim(VictimModel victim) {
        this.victim = victim;
    }

    public Integer getWar_id() {
        return war_id;
    }

    public void setWar_id(Integer war_id) {
        this.war_id = war_id;
    }
}
