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

class CharacterInfoModel {
    private Integer alliance_id;
    private Integer ancestry_id;
    private String birthday;
    private Integer bloodline_id;
    private Integer corporation_id;
    private String description;
    private String gender;
    private String name;
    private Integer race_id;
    private Float security_status;
    private String title;

    public Integer getAlliance_id() {
        return alliance_id;
    }

    public void setAlliance_id(Integer alliance_id) {
        this.alliance_id = alliance_id;
    }

    public Integer getAncestry_id() {
        return ancestry_id;
    }

    public void setAncestry_id(Integer ancestry_id) {
        this.ancestry_id = ancestry_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getBloodline_id() {
        return bloodline_id;
    }

    public void setBloodline_id(Integer bloodline_id) {
        this.bloodline_id = bloodline_id;
    }

    public Integer getCorporation_id() {
        return corporation_id;
    }

    public void setCorporation_id(Integer corporation_id) {
        this.corporation_id = corporation_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRace_id() {
        return race_id;
    }

    public void setRace_id(Integer race_id) {
        this.race_id = race_id;
    }

    public Float getSecurity_status() {
        return security_status;
    }

    public void setSecurity_status(Float security_status) {
        this.security_status = security_status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
