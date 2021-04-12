package com.estudo.space.config;

import com.estudo.space.service.StartupService;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("dev")
@EntityScan(basePackages = "com.estudo.space.domain")
@RequiredArgsConstructor
public class DevConfig {
    private final StartupService service;

    @Bean
    public boolean instantiateDatabase() throws Exception {
        service.instantiateDB();
        return true;
    }
}
