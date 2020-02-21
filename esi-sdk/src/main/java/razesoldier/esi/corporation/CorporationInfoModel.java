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

public class CorporationInfoModel {
    private Integer alliance_id;
    private Integer ceo_id;
    private Integer creator_id;
    private String date_founded;
    private String description;
    private Integer home_station_id;
    private Integer member_count;
    private String name;
    private Integer shares;
    private Float tax_rate;
    private String ticker;
    private String url;
    private Boolean war_eligible;

    public Integer getAlliance_id() {
        return alliance_id;
    }

    public void setAlliance_id(Integer alliance_id) {
        this.alliance_id = alliance_id;
    }

    public Integer getCeo_id() {
        return ceo_id;
    }

    public void setCeo_id(Integer ceo_id) {
        this.ceo_id = ceo_id;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    @Nullable
    public String getDate_founded() {
        return date_founded;
    }

    public void setDate_founded(String date_founded) {
        this.date_founded = date_founded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHome_station_id() {
        return home_station_id;
    }

    public void setHome_station_id(Integer home_station_id) {
        this.home_station_id = home_station_id;
    }

    public Integer getMember_count() {
        return member_count;
    }

    public void setMember_count(Integer member_count) {
        this.member_count = member_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Float getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(Float tax_rate) {
        this.tax_rate = tax_rate;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getWar_eligible() {
        return war_eligible;
    }

    public void setWar_eligible(Boolean war_eligible) {
        this.war_eligible = war_eligible;
    }
}
