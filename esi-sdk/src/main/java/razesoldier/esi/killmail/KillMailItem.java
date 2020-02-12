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

public class KillMailItem {
    private Integer flag;
    private Integer itemTypeId;
    private List<KillMailSubItem> items = new ArrayList<>();
    private Integer quantityDestroyed;
    private Integer quantityDropped;
    private Integer singleton;

    KillMailItem() {}

    public Integer getFlag() {
        return flag;
    }

    void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public List<KillMailSubItem> getItems() {
        return items;
    }

    void setItems(List<KillMailSubItem> items) {
        this.items = items;
    }

    public Integer getQuantityDestroyed() {
        return quantityDestroyed;
    }

    void setQuantityDestroyed(Integer quantityDestroyed) {
        this.quantityDestroyed = quantityDestroyed;
    }

    public Integer getQuantityDropped() {
        return quantityDropped;
    }

    void setQuantityDropped(Integer quantityDropped) {
        this.quantityDropped = quantityDropped;
    }

    public Integer getSingleton() {
        return singleton;
    }

    void setSingleton(Integer singleton) {
        this.singleton = singleton;
    }
}
