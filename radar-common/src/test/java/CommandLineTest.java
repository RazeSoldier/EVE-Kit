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

import org.junit.jupiter.api.Test;
import razesoldier.everadar.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandLineTest {
    @Test
    void test1() {
        String[] args = {"start", "--debug", "--log", "/log/1.txt"};
        var cli = CommandLine.parse(args);
        assertEquals("/log/1.txt", cli.getOption("log"));
        assertTrue(cli.hasParam("start"));
        assertEquals("true", cli.getOption("debug"));
    }

    @Test
    void test2() {
        String[] args = {"start", "--log", "/log/1.txt", "--debug"};
        var cli = CommandLine.parse(args);
        assertEquals("/log/1.txt", cli.getOption("log"));
        assertTrue(cli.hasParam("start"));
        assertEquals("true", cli.getOption("debug"));
    }
}
