package com.beakyn.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.beakyn.config.AbstractApplicationDataConfig;
import com.beakyn.config.MongoSystemProperties;

@Configuration
@Profile("test")
@ComponentScan(basePackages = "com.beakyn")
@PropertySource(name = "mongoProperties", value = "classpath:test-mongo-config.properties")
@EnableMongoRepositories(basePackages = "com.beakyn.dao")
public class ApplicationDataTestConfig extends AbstractApplicationDataConfig {

    private static final String HOME_DIR_PROPS_FILENAME = ".gwc.mongo.test.properties";

    @Autowired
    private Environment mongoProperties;

    @Bean
    @Override
    MongoSystemProperties getMongoSystemProperties() {
        return new MongoSystemProperties(mongoProperties, getHomeDirPropertiesFilename());
    }

    @Override
    String getHomeDirPropertiesFilename() {
        return HOME_DIR_PROPS_FILENAME;
    }
}
