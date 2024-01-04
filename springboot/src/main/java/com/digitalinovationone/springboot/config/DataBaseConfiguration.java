package com.digitalinovationone.springboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Getter
@Setter

// Privatization of Connection with the bank by profiles.
public class DataBaseConfiguration {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Profile("dev")
    @Bean
    public String developmentDatabaseConnection() {
        System.out.println("Database Connection for DEV");
        System.out.println(driverClassName);
        System.out.println(url);
        return "Database Connection to Development - Instance test";
    }


    @Profile("prod")
    @Bean
    public String productionDatabaseConnection() {
        System.out.println("Database Connection for PROD");
        System.out.println(driverClassName);
        System.out.println(url);
        return "Database Connection to Production - Instance test";
    }

}
