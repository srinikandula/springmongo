package com.beakyn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("default")
@ComponentScan(basePackages = "com.beakyn")
@PropertySource(name = "mongoProperties", value = "classpath:mongo-config.properties")
@EnableMongoRepositories(basePackages = "com.beakyn.dao")
public class ApplicationDataConfig extends AbstractApplicationDataConfig {

    private static final String HOME_DIR_PROPS_FILENAME = ".gwc.mongo.properties";

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
