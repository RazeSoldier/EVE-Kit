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

package razesoldier.sovradar;

import org.junit.jupiter.api.Test;
import razesoldier.esi.character.NotificationModel;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationModelTest {
    @Test
    void testEquals() {
        NotificationModel obj1 = new NotificationModel();
        obj1.setIs_read(false);
        obj1.setTimestamp("2000-07-01T00:00:01Z");
        obj1.setNotification_id(123456);
        obj1.setSender_id(2);
        NotificationModel obj2 = new NotificationModel();
        obj2.setIs_read(true);
        obj2.setTimestamp("2000-07-01T00:00:00Z");
        obj2.setNotification_id(123456);
        obj2.setSender_id(100);
        Set<NotificationModel> set = new HashSet<>();
        set.add(obj1);
        set.add(obj2);
        assertEquals(1, set.size());
    }
}
