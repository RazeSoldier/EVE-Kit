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

public class KillMail {
    private List<Attacker> attackers = new ArrayList<>();
    private Integer id;
    private String time;
    private Integer moonId;
    // Solar system that the kill took place in
    private Integer systemId;
    private Victim victim;
    private Integer warId;

    KillMail() {}

    public List<Attacker> getAttackers() {
        return attackers;
    }

    void setAttackers(List<Attacker> attackers) {
        this.attackers = attackers;
    }

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    void setTime(String time) {
        this.time = time;
    }

    public Integer getMoonId() {
        return moonId;
    }

    void setMoonId(Integer moonId) {
        this.moonId = moonId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Victim getVictim() {
        return victim;
    }

    void setVictim(Victim victim) {
        this.victim = victim;
    }

    public Integer getWarId() {
        return warId;
    }

    void setWarId(Integer warId) {
        this.warId = warId;
    }
}
