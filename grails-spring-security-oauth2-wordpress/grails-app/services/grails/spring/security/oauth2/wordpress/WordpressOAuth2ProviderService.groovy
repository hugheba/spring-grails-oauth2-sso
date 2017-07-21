package grails.spring.security.oauth2.wordpress

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import grails.converters.JSON
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken

class WordpressOAuth2ProviderService extends OAuth2AbstractProviderService {

    @Override
    String getProviderID() {
        return 'wordpress'
    }

    @Override
    Class<? extends DefaultApi20> getApiClass() {
        WordpressApi20.class
    }

    @Override
    String getProfileScope() {
        return 'http://localhost:8091/wp-json/wp/v2/users/me'
    }

    @Override
    String getScopes() {
        return "email"
    }

    @Override
    String getScopeSeparator() {
        return ","
    }

    @Override
    OAuth2SpringToken createSpringAuthToken(OAuth2AccessToken accessToken) {
        def user
        def response = getResponse(accessToken)
        try {
            log.debug("JSON response body: " + accessToken.rawResponse)
            user = JSON.parse(response.body)
        } catch (Exception exception) {
            log.error("Error parsing response from " + getProviderID() + ". Response:\n" + response.body)
            throw new OAuth2Exception("Error parsing response from " + getProviderID(), exception)
        }
        if (!user?.name) {
            log.error("No user email from " + getProviderID() + ". Response was:\n" + response.body)
            throw new OAuth2Exception("No user email from " + getProviderID())
        }
        new WordpressOAuth2SpringToken(accessToken, user?.name, providerID)
    }
}
