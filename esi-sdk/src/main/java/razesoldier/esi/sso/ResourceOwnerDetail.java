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

package razesoldier.esi.sso;

import org.jetbrains.annotations.NotNull;
import razesoldier.esi.model.ResourceOwnerDetailModel;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ResourceOwnerDetail {
    private Integer characterID;
    private String characterName;
    private ZonedDateTime expiresOn;
    private String scopes;
    private String tokenType;
    private String characterOwnerHash;
    private String intellectualProperty;

    public Integer getCharacterID() {
        return characterID;
    }

    void setCharacterID(Integer characterID) {
        this.characterID = characterID;
    }

    public String getCharacterName() {
        return characterName;
    }

    void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public ZonedDateTime getExpiresOn() {
        return expiresOn;
    }

    void setExpiresOn(ZonedDateTime expiresOn) {
        this.expiresOn = expiresOn;
    }

    public String getScopes() {
        return scopes;
    }

    void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getTokenType() {
        return tokenType;
    }

    void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getCharacterOwnerHash() {
        return characterOwnerHash;
    }

    void setCharacterOwnerHash(String characterOwnerHash) {
        this.characterOwnerHash = characterOwnerHash;
    }

    public String getIntellectualProperty() {
        return intellectualProperty;
    }

    void setIntellectualProperty(String intellectualProperty) {
        this.intellectualProperty = intellectualProperty;
    }

    @Override
    public String toString() {
        return "ResourceOwnerDetail{" +
                "characterID=" + characterID +
                ", characterName='" + characterName + '\'' +
                ", expiresOn=" + expiresOn +
                ", scopes='" + scopes + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", characterOwnerHash='" + characterOwnerHash + '\'' +
                ", intellectualProperty='" + intellectualProperty + '\'' +
                '}';
    }

    @NotNull
    static ResourceOwnerDetail newFromModel(@NotNull ResourceOwnerDetailModel model) {
        ResourceOwnerDetail detail = new ResourceOwnerDetail();
        detail.characterID = model.CharacterID;
        detail.characterName = model.CharacterName;
        detail.characterOwnerHash = model.CharacterOwnerHash;
        detail.expiresOn = ZonedDateTime.parse(model.ExpiresOn + "Z", DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("Z")));
        detail.intellectualProperty = model.IntellectualProperty;
        detail.scopes = model.Scopes;
        detail.tokenType = model.TokenType;
        return detail;
    }
}
