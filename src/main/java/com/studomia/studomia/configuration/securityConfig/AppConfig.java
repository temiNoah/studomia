package com.studomia.studomia.configuration.securityConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE )
//@PropertySource("classpath:application.properties")
//@ConfigurationProperties("greeting")
public class AppConfig {

   // @Value("${app.baseUrl}")
    private String  baseUrl   ;

    //@Value("${app.on.auth.success.redirect.path}")
    private String path  ;


    public AppConfig()
    {


    }
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//        return new PropertySourcesPlaceholderConfigurer().;
//    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
