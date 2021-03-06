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

module razesoldier.esi {
    requires static org.jetbrains.annotations;
    requires java.net.http; // Using build-in Http client
    requires org.apache.commons.lang3; // Used to provide some help function
    requires scribejava.core; // OAuth library
    requires java.desktop; // Used to open the browser
    requires nanohttpd; // build-in server

    opens razesoldier.esi.alliance to fastjson;
    opens razesoldier.esi.character to fastjson;
    opens razesoldier.esi.corporation to fastjson;
    opens razesoldier.esi.killmail to fastjson;
    opens razesoldier.esi.mail to fastjson;
    opens razesoldier.esi.universe to fastjson;

    exports razesoldier.esi.alliance;
    exports razesoldier.esi.character;
    exports razesoldier.esi.corporation;
    exports razesoldier.esi.error;
    exports razesoldier.esi.killmail;
    exports razesoldier.esi.mail;
    exports razesoldier.esi.model; // The client shouldn't call the class in this package because Fastjson library is automatic module.
    exports razesoldier.esi.search;
    exports razesoldier.esi.sso;
    exports razesoldier.esi.status;
    exports razesoldier.esi.universe;
}
