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

package razesoldier.structureradar;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.sso.SSOLogin;

/**
 * 存储监视者的信息，特别是授权信息
 */
class Watcher {
    private final String name;
    private final SSOLogin loginService;
    private final Integer uid;

    private Watcher(@NotNull String name, @NotNull SSOLogin loginService, @NotNull Integer uid) {
        this.loginService = loginService;
        this.uid = uid;
        this.name = name;
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static Watcher newInstance(@NotNull String name, int uid, @NotNull String refreshToken) {
        SSOLogin login = new SSOLogin("50514d86188542438ce6c5c3a21eeb62", "https://eve.razesoldier.cn/eve-sso/callback");
        login.setRefreshToken(refreshToken);
        return new Watcher(name, login, uid);
    }

    Integer getUid() {
        return uid;
    }

    SSOLogin getLoginService() {
        return loginService;
    }

    String getName() {
        return name;
    }

    /**
     * 检查监视者的刷新令牌是否有效
     *
     * @return 有效返回true，反之亦然
     */
    boolean checkRefreshToken() {
        try {
            loginService.fetchRefreshingCode();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
