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

/**
 * Used to build {@link LoginService}.
 *
 * <h2>Build Way</h2>
 * The builder provides two ways to build the service.
 * <h3>Reuse refresh code</h3>
 * <pre>
 * LoginServiceBuilder builder = new LoginServiceBuilder(appId);
 * LoginService service = {@link LoginServiceBuilder#newFromRefreshToken(String) builder.newFromRefreshToken(code)};
 * </pre>
 * Refresh tokens can be obtained from previous login, or other sources.
 * <h3>Fresh new Login</h3>
 */
public class LoginServiceBuilder {
    private final LoginService service;

    public LoginServiceBuilder(@NotNull String appId) {
        this.service = new LoginService(appId);
    }

    public LoginServiceBuilder(@NotNull String appId, @NotNull String defaultScope) {
        this.service = new LoginService(appId, defaultScope);
    }

    /**
     * Login from refresh token.
     */
    public LoginService newFromRefreshToken(@NotNull String refreshCode) {
        return service.setRefreshToken(refreshCode);
    }
}
