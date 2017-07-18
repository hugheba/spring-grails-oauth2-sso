package sso.grails.demo.services

import com.test.sso.domain.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetailsService implements UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.test.sso.domain.User person = com.test.sso.domain.User.findByUsername(username)
        if (person == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username))
        }
        new User(person.username, person.password, loadAuthorities(person))
    }

    private Collection<GrantedAuthority> loadAuthorities(com.test.sso.domain.User person) {
        Collection<Role> userAuthorities = person.authorities
        userAuthorities.collect { new SimpleGrantedAuthority(it.authority) }
    }
}
