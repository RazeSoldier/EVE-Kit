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

package razesoldier.esi.corporation;

import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;

public class CorporationInfo {
    private Integer allianceId;
    private Integer ceoId;
    private Integer creatorId;
    private ZonedDateTime dateFounded;
    private String description;
    private Integer homeStationId;
    private Integer memberCount;
    private String name;
    private Long shares;
    private Float taxRate;
    private String ticker;
    private String url;
    private Boolean warEligible;

    public Integer getAllianceId() {
        return allianceId;
    }

    void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public Integer getCeoId() {
        return ceoId;
    }

    void setCeoId(Integer ceoId) {
        this.ceoId = ceoId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    @Nullable
    public ZonedDateTime getDateFounded() {
        return dateFounded;
    }

    void setDateFounded(ZonedDateTime dateFounded) {
        this.dateFounded = dateFounded;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Integer getHomeStationId() {
        return homeStationId;
    }

    void setHomeStationId(Integer homeStationId) {
        this.homeStationId = homeStationId;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Long getShares() {
        return shares;
    }

    void setShares(Long shares) {
        this.shares = shares;
    }

    public Float getTaxRate() {
        return taxRate;
    }

    void setTaxRate(Float taxRate) {
        this.taxRate = taxRate;
    }

    public String getTicker() {
        return ticker;
    }

    void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getUrl() {
        return url;
    }

    void setUrl(String url) {
        this.url = url;
    }

    public Boolean getWarEligible() {
        return warEligible;
    }

    void setWarEligible(Boolean warEligible) {
        this.warEligible = warEligible;
    }
}
