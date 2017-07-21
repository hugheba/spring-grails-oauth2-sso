package grails.spring.security.oauth2.wordpress

import com.github.scribejava.core.model.OAuth2AccessToken
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken

class WordpressOAuth2SpringToken extends OAuth2SpringToken {

    private String username
    private String providerId

    WordpressOAuth2SpringToken(OAuth2AccessToken accessToken, String email, String providerId) {
        super(accessToken)
        this.username = username
        this.providerId = providerId
    }

    @Override
    String getProviderName() {
        return providerId
    }

    @Override
    String getSocialId() {
        return username
    }

    @Override
    String getScreenName() {
        return username
    }
}
