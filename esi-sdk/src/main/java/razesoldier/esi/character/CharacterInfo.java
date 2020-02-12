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

package razesoldier.esi.character;

import java.time.ZonedDateTime;

public class CharacterInfo {
    private Integer allianceId;
    private Integer ancestryId;
    private ZonedDateTime birthday;
    private Integer bloodlineId;
    private Integer corporationId;
    private String description;
    private Gender gender;
    private String name;
    private Integer raceId;
    private Float securityStatus;
    private String title;

    CharacterInfo() {}

    public Integer getAllianceId() {
        return allianceId;
    }

    void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public Integer getAncestryId() {
        return ancestryId;
    }

    void setAncestryId(Integer ancestryId) {
        this.ancestryId = ancestryId;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    public Integer getBloodlineId() {
        return bloodlineId;
    }

    void setBloodlineId(Integer bloodlineId) {
        this.bloodlineId = bloodlineId;
    }

    public Integer getCorporationId() {
        return corporationId;
    }

    void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Gender getGender() {
        return gender;
    }

    void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Integer getRaceId() {
        return raceId;
    }

    void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    public Float getSecurityStatus() {
        return securityStatus;
    }

    void setSecurityStatus(Float securityStatus) {
        this.securityStatus = securityStatus;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CharacterInfo{\n" +
                " allianceId=" + allianceId +
                ",\n ancestryId=" + ancestryId +
                ",\n birthday=" + birthday +
                ",\n bloodlineId=" + bloodlineId +
                ",\n corporationId=" + corporationId +
                ",\n description='" + description + '\'' +
                ",\n gender=" + gender +
                ",\n name='" + name + '\'' +
                ",\n raceId=" + raceId +
                ",\n securityStatus=" + securityStatus +
                ",\n title='" + title + '\'' +
                "\n}";
    }
}
