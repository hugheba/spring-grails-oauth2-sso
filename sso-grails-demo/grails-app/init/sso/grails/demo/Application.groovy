package sso.grails.demo

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService
import sso.grails.demo.services.CustomUserDetailsService

class Application extends GrailsAutoConfiguration {

    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService()
    }

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}