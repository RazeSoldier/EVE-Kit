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

public class AllianceInfoModel {
    private Integer creator_corporation_id;
    private Integer creator_id;
    private String date_founded;
    private Integer executor_corporation_id;
    private String name;
    private String ticker;

    public Integer getCreator_corporation_id() {
        return creator_corporation_id;
    }

    public void setCreator_corporation_id(Integer creator_corporation_id) {
        this.creator_corporation_id = creator_corporation_id;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public String getDate_founded() {
        return date_founded;
    }

    public void setDate_founded(String date_founded) {
        this.date_founded = date_founded;
    }

    public Integer getExecutor_corporation_id() {
        return executor_corporation_id;
    }

    public void setExecutor_corporation_id(Integer executor_corporation_id) {
        this.executor_corporation_id = executor_corporation_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
