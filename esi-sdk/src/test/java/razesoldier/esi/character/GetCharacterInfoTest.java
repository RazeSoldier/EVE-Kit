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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import razesoldier.esi.error.ConnectionException;

import java.text.ParseException;
import java.time.ZonedDateTime;

public class GetCharacterInfoTest {
    @Test
    void testSearch() throws ConnectionException, ParseException {
        CharacterInfo info = new GetCharacterInfo().getInfo(2112309917); // 2112309917 is my id :)
        assertEquals("星耀晨曦", info.getTitle());
        assertEquals("Starshine Morning", info.getName());
        assertEquals(Gender.male, info.getGender());
        assertEquals(ZonedDateTime.parse("2017-01-27T09:04:02+00:00[UTC]"), info.getBirthday());
    }
}
