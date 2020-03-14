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

import com.alibaba.fastjson.JSON;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.jetbrains.annotations.NotNull;
import razesoldier.esi.model.ResourceOwnerDetailModel;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

/**
 * Main entry that used to login ESI.
 * For login step, see <a href="https://docs.esi.evetech.net/docs/sso/native_sso_flow.html">docs</a>.
 */
public class LoginService {
    public static void main(String[] argv) throws FetchAccessCodeException, FetchProtectedResourceException {
        final String vvvjjj = "3WDhU7BR1B8OYTA_p4iUGXyPdqNbJBd7sfCHOnUYxG0";
        LoginService loginService = new LoginServiceBuilder("50514d86188542438ce6c5c3a21eeb62").newFromRefreshCode(vvvjjj);
        loginService.fetchAccessCode();
        System.out.println(loginService.accessToken.getAccessToken());
    }

    /**
     * The primary reason for using the state parameter is to mitigate CSRF attacks.
     */
    private final String secretState = "secret" + new SecureRandom().nextInt(999_999);
    private final OAuth20Service service; // ESI use OAuth 2.0
    private OAuth2AccessToken accessToken = new OAuth2AccessToken("");
    /**
     * Access token expiration time.
     */
    private Instant expiresTime = Instant.MIN;

    /**
     * Get {@link ResourceOwnerDetail} from ESI server.
     *
     * @return {@link ResourceOwnerDetail} instance
     * @throws FetchProtectedResourceException Exception thrown if an error which fetching the detail.
     */
    public ResourceOwnerDetail getResourceOwnerDetails() throws FetchProtectedResourceException {
        final String url = "https://login.eveonline.com/oauth/verify";
        final OAuthRequest request = new OAuthRequest(Verb.GET, url);
        if (Instant.now().isAfter(expiresTime)) {
            try {
                fetchAccessCode();
            } catch (FetchAccessCodeException e) {
                throw new FetchProtectedResourceException(e);
            }
        }
        service.signRequest(accessToken, request);
        try (Response response = service.execute(request)) {
            return ResourceOwnerDetail.newFromModel(JSON.parseObject(response.getBody(), ResourceOwnerDetailModel.class));
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new FetchProtectedResourceException(e);
        }
    }

    LoginService(@NotNull String appId) {
        service = new ServiceBuilder(appId).build(new TranquilityAPI());
    }

    LoginService(@NotNull String appId, @NotNull String defaultScope) {
        service = new ServiceBuilder(appId).defaultScope(defaultScope).build(new TranquilityAPI());
    }

    LoginService setRefreshCode(@NotNull String refreshCode) {
        accessToken = new OAuth2AccessToken(accessToken.getAccessToken(), null, null, refreshCode,
                null, null);
        return this;
    }

    /**
     * Fetch a new access code from the refresh code
     *
     * @throws FetchAccessCodeException Exception thrown if an error which fetching access code.
     */
    private void fetchAccessCode() throws FetchAccessCodeException {
        try {
            accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
            expiresTime = Instant.now().plusSeconds(accessToken.getExpiresIn());
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new FetchAccessCodeException(e);
        }
    }
}