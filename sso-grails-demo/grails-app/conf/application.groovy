// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.test.sso.domain.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.test.sso.domain.UserRole'
grails.plugin.springsecurity.authority.className = 'com.test.sso.domain.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/', access: ['permitAll']],
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/index', access: ['permitAll']],
        [pattern: '/index.gsp', access: ['permitAll']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**', filters: 'none'],
        [pattern: '/**/js/**', filters: 'none'],
        [pattern: '/**/css/**', filters: 'none'],
        [pattern: '/**/images/**', filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**', filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.oauth2.active = true
grails.plugin.springsecurity.oauth2.registration.askToLinkOrCreateAccountUri = '/oauth2/ask'
grails.plugin.springsecurity.oauth2.registration.roleNames = ['ROLE_SUBSCRIBER']
grails.plugin.springsecurity.oauth2.providers.wordpress.api_key='YUVBfTCei28UciXaWWNbRGSSfH9hBv'
grails.plugin.springsecurity.oauth2.providers.wordpress.api_secret='69M9twm51UrpRObklmdGIV1np1WIbI'
grails.plugin.springsecurity.oauth2.providers.wordpress.successUri='/oauth2/wordpress/success'
grails.plugin.springsecurity.oauth2.providers.wordpress.failureUri='/oauth2/wordpress/failure'
grails.plugin.springsecurity.oauth2.providers.wordpress.callback='/oauth2/wordpress/callback'
//grails.plugin.springsecurity.oauth2.providers.wordpress.scopes=''

// Added by the Spring Security OAuth2 Google Plugin:
grails.plugin.springsecurity.oauth2.domainClass = 'com.test.sso.domain.OAuthID'
