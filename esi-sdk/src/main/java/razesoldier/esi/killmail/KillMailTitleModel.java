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

/**
 * For internal use only. External use is prohibited, use {@link KillMailTitle} instead.
 */
public class KillMailTitleModel {
    private String killmail_hash;
    private long killmail_id;

    public long getId() {
        return killmail_id;
    }

    public String getHash() {
        return killmail_hash;
    }

    public void setKillmail_hash(String hash) {
        this.killmail_hash = hash;
    }

    public void setKillmail_id(long id) {
        this.killmail_id = id;
    }
}
