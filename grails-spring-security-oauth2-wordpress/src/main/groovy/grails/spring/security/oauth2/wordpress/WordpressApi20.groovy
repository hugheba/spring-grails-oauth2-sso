package grails.spring.security.oauth2.wordpress

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuthConfig
import com.github.scribejava.core.model.Verb
import com.github.scribejava.core.utils.OAuthEncoder
import com.github.scribejava.core.utils.Preconditions

/**
 * @url http://www.pac4j.org/apidocs/pac4j/1.9.1/org/pac4j/oauth/client/WordPressClient.html
 */
class WordpressApi20 extends DefaultApi20 {
    private static final String BASE_URL = "https://localhost:8091/oauth2/";

    private static final String AUTHORIZE_URL = BASE_URL + "authorize?client_id=%s&redirect_uri=%s&response_type=code";

    protected WordpressApi20() {}

    private static class InstanceHolder {
        private static final WordpressApi20 INSTANCE = new WordpressApi20()
    }

    static WordpressApi20 instance() {
        return  InstanceHolder.INSTANCE
    }

    @Override
    public String getAccessTokenEndpoint() {
        return BASE_URL + "token";
    }

    @Override
    public String getAuthorizationUrl(final OAuthConfig config, Map<String, String> additionalParams) {
        Preconditions.checkValidUrl(config.getCallback(),
                "Must provide a valid url as callback. WordPress does not support OOB");

        return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return BASE_URL+"authorize";
    }
}
