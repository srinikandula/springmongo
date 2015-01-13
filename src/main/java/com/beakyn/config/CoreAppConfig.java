package com.beakyn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.beakyn.SystemProperties;

@Configuration
@ComponentScan(basePackages = "com.beakyn")
@PropertySource("classpath:app-config.properties")
public class CoreAppConfig {

    @Autowired
    private Environment env;

    @Bean
    public SystemProperties props() {
        return new SystemProperties(env);
    }
    
    
}
