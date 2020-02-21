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

package razesoldier.esi.alliance;

import java.time.ZonedDateTime;

public class AllianceInfo {
    private Integer creatorCorporationId;
    private Integer creatorId;
    private ZonedDateTime dateFounded;
    private Integer executorCorporationId;
    private String name;
    private String ticker;

    AllianceInfo() {}

    public Integer getCreatorCorporationId() {
        return creatorCorporationId;
    }

    void setCreatorCorporationId(Integer creatorCorporationId) {
        this.creatorCorporationId = creatorCorporationId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public ZonedDateTime getDateFounded() {
        return dateFounded;
    }

    void setDateFounded(ZonedDateTime dateFounded) {
        this.dateFounded = dateFounded;
    }

    public Integer getExecutorCorporationId() {
        return executorCorporationId;
    }

    void setExecutorCorporationId(Integer executorCorporationId) {
        this.executorCorporationId = executorCorporationId;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
