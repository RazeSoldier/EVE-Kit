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

package razesoldier.esi.mail;

import org.jetbrains.annotations.NotNull;

public class Recipient {
    private RecipientType type;
    private Integer id;

    public Recipient(RecipientType type, Integer id) {
        this.type = type;
        this.id = id;
    }

    public RecipientType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public enum RecipientType {
        Alliance("alliance"), Corporation("corporation"), Character("character"),
        MailingList("mailing_list");

        private String text;

        RecipientType(@NotNull String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
