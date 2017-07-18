package com.test.sso

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableWebMvc
class UiWebConfig extends WebMvcConfigurerAdapter {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer()
    }

    @Override
    void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable()
    }

    @Override
    void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry)
        registry
                .addViewController("/")
                .setViewName("forward:/index")
        registry
                .addViewController("/index")
        registry
                .addViewController("/securedPage")
    }

}
