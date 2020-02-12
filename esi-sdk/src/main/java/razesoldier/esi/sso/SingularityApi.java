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

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.OutputStream;

/**
 * This class defines the API of EVE's test server
 */
class SingularityApi extends DefaultApi20 {
    public String getAccessTokenEndpoint() {
        return "https://sisilogin.testeveonline.com/v2/oauth/token";
    }

    protected String getAuthorizationBaseUrl() {
        return "https://sisilogin.testeveonline.com/v2/oauth/authorize/";
    }

    /**
     * Use our own service.
     */
    @Override
    public OAuth20Service createService(String apiKey, String apiSecret, String callback, String defaultScope, String responseType,
            OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,
            HttpClient httpClient) {
        return new OAuthService(this, apiKey, apiSecret, callback, defaultScope, responseType, debugStream, userAgent,
                httpClientConfig, httpClient);
    }
}
