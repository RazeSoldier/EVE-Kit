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

package razesoldier.esi.internal;

import org.junit.jupiter.api.Test;
import razesoldier.esi.error.InvalidStringException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class StrMap2MapTest {
    @Test
    void testConvert() throws InvalidStringException {
        Map<String, String> expected = new HashMap<>();
        expected.put("players", "11579");
        expected.put("server_version", "1648688");
        expected.put("start_time", "2020-01-18T11:02:36Z");
        Map<String, String> actual = StrMap2Map.convert(
                "{\"players\":11579,\"server_version\":\"1648688\",\"start_time\":\"2020-01-18T11:02:36Z\"}"
        );
        assertEquals(expected, actual);
    }
}
