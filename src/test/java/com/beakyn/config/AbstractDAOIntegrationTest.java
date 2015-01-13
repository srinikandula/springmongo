package com.beakyn.config;


import lombok.Getter;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DB;

@ActiveProfiles("test")
@ContextConfiguration(classes = { ApplicationDataTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractDAOIntegrationTest {

    @Autowired
    @Getter
    private MongoTemplate mongoTemplate;

    protected DB getDB() {
        return mongoTemplate.getDb();
    }
}
