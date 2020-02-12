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

public class Victim {
    private Integer allianceId;
    private Integer characterId;
    private Integer corporationId;
    private Integer damageTaken;
    private Integer factionId;
    private List<KillMailItem> items = new ArrayList<>();
    private KillMailPosition position;
    private Integer shipTypeId;

    public Integer getAllianceId() {
        return allianceId;
    }

    void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getCorporationId() {
        return corporationId;
    }

    void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }

    public Integer getDamageTaken() {
        return damageTaken;
    }

    void setDamageTaken(Integer damageTaken) {
        this.damageTaken = damageTaken;
    }

    public Integer getFactionId() {
        return factionId;
    }

    void setFactionId(Integer factionId) {
        this.factionId = factionId;
    }

    public List<KillMailItem> getItems() {
        return items;
    }

    void setItems(List<KillMailItem> items) {
        this.items = items;
    }

    public KillMailPosition getPosition() {
        return position;
    }

    void setPosition(KillMailPosition position) {
        this.position = position;
    }

    public Integer getShipTypeId() {
        return shipTypeId;
    }

    void setShipTypeId(Integer shipTypeId) {
        this.shipTypeId = shipTypeId;
    }
}
