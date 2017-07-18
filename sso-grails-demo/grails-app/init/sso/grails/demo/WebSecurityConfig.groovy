package sso.grails.demo

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PrincipalExtractor principalExtractor() {
        return new PrincipalExtractor() {
            @Override
            Object extractPrincipal(Map<String, Object> map) {
                return map?.name
            }
        }
    }

    @Override
    protected void configure(HttpSecurity http) {
        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**", "/error").permitAll()
                .anyRequest().authenticated()
    }
}