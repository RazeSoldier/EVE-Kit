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

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.AuthorizationUrlBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import fi.iki.elonen.NanoHTTPD;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import razesoldier.esi.internal.HttpClientFactory;

import java.io.IOException;

import java.net.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main entry that used to login ESI.
 * For login step, see <a href="https://docs.esi.evetech.net/docs/sso/native_sso_flow.html">docs</a>.
 */
public class SSOLogin {
    // The primary reason for using the state parameter is to mitigate CSRF attacks.
    private final String secretState = "secret" + new SecureRandom().nextInt(999_999);
    private final OAuth20Service service; // ESI use OAuth 2.0
    private final AuthorizationUrlBuilder authorizationUrlBuilder;
    private OAuth2AccessToken accessToken;

    public SSOLogin(@NotNull String clientID, @NotNull String callbackURL) {
        service = new ServiceBuilder(clientID).callback(callbackURL).build(new TranquilityAPI());
        authorizationUrlBuilder = service.createAuthorizationUrlBuilder()
                .state(secretState)
                .initPKCE();
    }

    public SSOLogin(@NotNull String clientID, @NotNull String callbackURL, @NotNull String scope) {
        service = new ServiceBuilder(clientID).callback(callbackURL).defaultScope(scope).build(new TranquilityAPI());
        authorizationUrlBuilder = service.createAuthorizationUrlBuilder()
                .state(secretState)
                .initPKCE();
    }

    /**
     * Try to fetch a access token.
     * @throws GetAccessTokenException Exception thrown if an error occurred while fetch the code
     */
    public SSOLogin fetchAccessToken() throws GetAccessTokenException {
        String code;
        try {
            openUserBrowser(authorizationUrlBuilder.build());
            String[] authCodeRes = getAuthCodeFromBuildInServer();
            if (!authCodeRes[1].equals(secretState)) {
                throw new GetAccessTokenException("Ooops, state value does not match!");
            }
            code = authCodeRes[0];
        } catch (IOException e) {
            throw new GetAccessTokenException(e);
        }
        try {
            accessToken = service.getAccessToken(AccessTokenRequestParams.create(code)
                    .pkceCodeVerifier(authorizationUrlBuilder.getPkce().getCodeVerifier()));
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new GetAccessTokenException(e);
        }
        return this;
    }

    /**
     * Open the browser with the given URL.
     * @param url URL will to open
     * @throws IOException if the user default browser is not found,
     * or it fails to be launched, or the default handler application
     * failed to be launched
     */
    private void openUserBrowser(@NotNull String url) throws IOException {
        if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    dp.browse(URI.create(url));
                }
        } else {
            throw new RuntimeException("The Java unsupported awt dsktop");
        }
    }

    /**
     * Get authorization code.
     * We create a local server to listen for callback URL,
     * then we don’t need the user to enter the code manually.
     * Once we accept the browser, we can analyze the requested URL to parse out what we need next.
     * @return An string array, [0 => code, 1 => state]
     * @throws GetAccessTokenException Exception thrown if an error occurred while fetch the code
     */
    @NotNull
    private String[] getAuthCodeFromBuildInServer() throws GetAccessTokenException {
        BuildInServer server = new BuildInServer("localhost", 80);
        try {
            server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            throw new GetAccessTokenException(e);
        }

        NanoHTTPD.IHTTPSession session;
        while (true) {
            session = server.getSession();
            if (session != null) {
                break;
            }
            try {
                Thread.sleep(1000); // Wake up every 1 second
            } catch (InterruptedException e) {
                throw new GetAccessTokenException(e);
            }
        }
        server.stop();
        String res = session.getQueryParameterString();
        Pattern codePattern = Pattern.compile("code=([a-zA-Z-0-9_]*)");
        Pattern statePattern = Pattern.compile("state=([0-9a-z]*)");
        Matcher codeMatcher = codePattern.matcher(res);
        Matcher stateMatcher = statePattern.matcher(res);
        String[] result = new String[2];
        if (!codeMatcher.find()) {
            throw new GetAccessTokenException("Failed to parse: " + res);
        }
        if (codeMatcher.group(1).isEmpty()) {
            throw new GetAccessTokenException("Failed to parse: " + res);
        }
        result[0] = codeMatcher.group(1);
        if (!stateMatcher.find()) {
            throw new GetAccessTokenException("Failed to parse: " + res);
        }
        if (stateMatcher.group(1).isEmpty()) {
            throw new GetAccessTokenException("Failed to parse: " + res);
        }
        result[1] = stateMatcher.group(1);
        return result;
    }

    private static class BuildInServer extends NanoHTTPD {
        private IHTTPSession session;

        public BuildInServer(String hostname, int port) {
            super(hostname, port);
        }

        @Override
        public Response serve(IHTTPSession session) {
            this.session = session;
            return newFixedLengthResponse("<html>\n" +
                    "    <head>\n" +
                    "        <meta content=\"text/html; charset=utf-8\" http-equiv=\"content-type\">" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "         可以关闭此选项卡\n" +
                    "    </body>\n" +
                    "</html>");
        }

        @Nullable
        public IHTTPSession getSession() {
            return session;
        }
    }

    public SSOLogin fetchRefreshingCode() throws GetAccessTokenException {
        try {
            accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new GetAccessTokenException(e);
        }
        return this;
    }

    public SSOLogin fetchRefreshingCode(String scope) throws GetAccessTokenException {
        try {
            accessToken = service.refreshAccessToken(accessToken.getRefreshToken(), scope);
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new GetAccessTokenException(e);
        }
        return this;
    }

    public SSOLogin setRefreshToken(@NotNull String refreshToken) {
        if (accessToken == null) {
            accessToken = new OAuth2AccessToken("", null, null,
                    refreshToken, null, null);
        } else {
            accessToken = new OAuth2AccessToken(accessToken.getAccessToken(), accessToken.getTokenType(), accessToken.getExpiresIn(),
                    refreshToken, accessToken.getScope(), accessToken.getRawResponse());
        }
        return this;
    }

    public Map<String, String> fetchProtectedResource(@Nullable String url) throws FetchProtectedResourceException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, url);
        service.signRequest(accessToken, request);
        try (Response response = service.execute(request)) {
            Map<String, String> res = new HashMap<>();
            res.put("code", String.valueOf(response.getCode()));
            res.put("body", response.getBody());
            return res;
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new FetchProtectedResourceException(e);
        }
    }

    public Map<String, String> postProtectedResource(@NotNull String url, @NotNull String body)
            throws FetchProtectedResourceException {
        final HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken.getAccessToken())
                .method("POST", HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClientFactory.getInstance().getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new FetchProtectedResourceException(e);
        }
        Map<String, String> resp = new HashMap<>();
        resp.put("code", String.valueOf(response.statusCode()));
        resp.put("body", response.body());
        return resp;
    }
}
