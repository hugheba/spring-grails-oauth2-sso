package com.test.sso

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.context.request.RequestContextListener

@EnableOAuth2Sso
@SpringBootApplication
class UiApplication extends WebSecurityConfigurerAdapter {
    @Override
    void configure(HttpSecurity http) {
        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .anyRequest().authenticated()
    }

    @Bean
    RequestContextListener requestContextListener() {
        return new RequestContextListener()
    }

    @Bean
    PrincipalExtractor principalExtractor() {
        return new PrincipalExtractor() {
            @Override
            Object extractPrincipal(Map<String, Object> map) {
                return map?.name
            }
        }
    }

    static void main(String[] args) {
        SpringApplication.run(UiApplication, args)
    }
}
