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

import org.junit.jupiter.api.Test;
import razesoldier.esi.error.HttpRequestException;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class GetCorporationInfoTest {
    @Test
    void testSearch() throws HttpRequestException, ParseException {
        GetCorporationInfo getter = new GetCorporationInfo();
        CorporationInfo info = getter.query(98608055);
        assertEquals(2113820265, info.getCeoId());
        assertEquals("G.DL", info.getTicker());
    }
}
