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

public class KillMailSubItemModel {
    private Integer flag;
    private Integer item_type_id;
    private Integer quantity_destroyed;
    private Integer quantity_dropped;
    private Integer singleton;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getItem_type_id() {
        return item_type_id;
    }

    public void setItem_type_id(Integer item_type_id) {
        this.item_type_id = item_type_id;
    }

    public Integer getQuantity_destroyed() {
        return quantity_destroyed;
    }

    public void setQuantity_destroyed(Integer quantity_destroyed) {
        this.quantity_destroyed = quantity_destroyed;
    }

    public Integer getQuantity_dropped() {
        return quantity_dropped;
    }

    public void setQuantity_dropped(Integer quantity_dropped) {
        this.quantity_dropped = quantity_dropped;
    }

    public Integer getSingleton() {
        return singleton;
    }

    public void setSingleton(Integer singleton) {
        this.singleton = singleton;
    }
}
