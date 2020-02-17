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

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.character.CharacterInfo;
import razesoldier.esi.character.GetCharacterInfo;
import razesoldier.esi.error.ConnectionException;
import razesoldier.esi.sso.FetchProtectedResourceException;
import razesoldier.esi.sso.GetAccessTokenException;
import razesoldier.esi.sso.SSOLogin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Get a single killmail.
 * Api version: v1
 */
public class GetKillMail {
    private SSOLogin loginService;

    public GetKillMail(@NotNull SSOLogin loginService) {
        this.loginService = loginService;
    }

    @NotNull
    public KillMail getKillMail(long id, @NotNull String hash) throws FetchProtectedResourceException {
        final String endpoint = "https://esi.evetech.net/v1/killmails/%d/%s/?datasource=tranquility";
        Map<String, String> res = loginService.fetchProtectedResource(String.format(endpoint, id, hash));
        KillMailModel kmm = JSON.parseObject(res.get("body"), KillMailModel.class);

        KillMail km = new KillMail();

        List<Attacker> attackers = new ArrayList<>();
        kmm.getAttackers().forEach(attackerModel -> {
            Attacker attacker = new Attacker();
            attacker.setAllianceId(attackerModel.getAlliance_id());
            attacker.setCharacterId(attackerModel.getCharacter_id());
            attacker.setCorporationId(attackerModel.getCorporation_id());
            attacker.setDamageDone(attackerModel.getDamage_done());
            attacker.setFactionId(attackerModel.getFaction_id());
            attacker.setFinalBlow(attackerModel.isFinal_blow());
            attacker.setSecurityStatus(attackerModel.getSecurity_status());
            attacker.setShipTypeId(attackerModel.getShip_type_id());
            attacker.setWeaponTypeId(attackerModel.getWeapon_type_id());
            attackers.add(attacker);
        });
        km.setAttackers(attackers);

        km.setId(kmm.getKillmail_id());
        km.setTime(kmm.getKillmail_time());
        km.setMoonId(kmm.getMoon_id());
        km.setSystemId(kmm.getSolar_system_id());
        km.setWarId(kmm.getWar_id());

        Victim victim = new Victim();
        VictimModel victimModel = kmm.getVictim();
        victim.setAllianceId(victimModel.getAlliance_id());
        victim.setCharacterId(victimModel.getCharacter_id());
        victim.setCorporationId(victimModel.getCorporation_id());
        victim.setDamageTaken(victimModel.getDamage_taken());
        victim.setFactionId(victimModel.getFaction_id());

        KillMailPosition position = new KillMailPosition();
        KillMailPositionModel positionModel = new KillMailPositionModel();
        position.setX(positionModel.getX());
        position.setY(positionModel.getY());
        position.setZ(positionModel.getZ());
        victim.setPosition(position);

        victim.setShipTypeId(victimModel.getShip_type_id());

        List<KillMailItem> items = new ArrayList<>();
        victimModel.getItems().forEach(killMailItemModel -> {
            KillMailItem item = new KillMailItem();
            item.setFlag(killMailItemModel.getFlag());
            item.setItemTypeId(killMailItemModel.getItem_type_id());
            item.setQuantityDestroyed(killMailItemModel.getQuantity_destroyed());
            item.setQuantityDropped(killMailItemModel.getQuantity_dropped());
            item.setSingleton(killMailItemModel.getSingleton());
            List<KillMailSubItem> subItems = new ArrayList<>();
            killMailItemModel.getItems().forEach(killMailSubItemModel -> {
                KillMailSubItem subItem = new KillMailSubItem();
                subItem.setFlag(killMailSubItemModel.getFlag());
                subItem.setItemTypeId(killMailSubItemModel.getItem_type_id());
                subItem.setQuantityDestroyed(killMailSubItemModel.getQuantity_destroyed());
                subItem.setQuantityDropped(killMailSubItemModel.getQuantity_dropped());
                subItem.setSingleton(killMailSubItemModel.getSingleton());
                subItems.add(subItem);
            });
            item.setItems(subItems);
            items.add(item);
        });
        victim.setItems(items);

        km.setVictim(victim);
        return km;
    }
}
