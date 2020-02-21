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

import org.junit.jupiter.api.Test;
import razesoldier.esi.error.HttpRequestException;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

public class GetAllianceInfoTest {
    @Test
    void testQuery() throws HttpRequestException, ParseException {
        GetAllianceInfo getter = new GetAllianceInfo();
        AllianceInfo info = getter.query(99009310);
        assertEquals("VENI VIDI VICI.", info.getName());
        assertEquals("-VVV-", info.getTicker());
        assertEquals(98602384, info.getCreatorCorporationId());
    }
}
