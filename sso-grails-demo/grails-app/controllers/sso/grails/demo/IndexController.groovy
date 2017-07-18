package sso.grails.demo

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.userdetails.UserDetails

class IndexController {

    UserDetails userDetails

    def index() { }

    @PreAuthorize(value="isFullyAuthenticated() and hasRole('ROLE_USER')")
    def securedPage() {
        [username: userDetails.username]
    }
}
