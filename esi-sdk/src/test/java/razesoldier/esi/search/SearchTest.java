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

package razesoldier.esi.search;

import org.junit.jupiter.api.Test;
import razesoldier.esi.error.ConnectionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    @Test
    void testGenerateUrl() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Search.class.getDeclaredMethod("generateUrl", SearchType[].class, String.class, boolean.class);
        method.setAccessible(true);
        String url = (String) method.invoke(new Search(), new SearchType[]{SearchType.solarSystem}, "1QH-0K", false);
        assertEquals("https://esi.evetech.net/v2/search/?categories=solar_system&datasource=tranquility&search=1QH-0K&strict=false",
                url);
    }

    @Test
    void testDoSearch() throws ConnectionException {
        Search search = new Search();
        Integer id = (Integer) search.doSearch(new SearchType[]{SearchType.solarSystem}, "1QH-0K", false).
                getJSONArray(SearchType.solarSystem.toString()).get(0);
        assertEquals(30003617, id);
    }
}
