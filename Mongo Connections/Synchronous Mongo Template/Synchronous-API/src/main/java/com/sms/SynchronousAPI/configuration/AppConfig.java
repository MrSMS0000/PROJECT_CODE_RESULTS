package com.sms.SynchronousAPI.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

    public static final String dataBaseName = "Testing";
    public static final String collectionName = "api";

    private static final String connectionString =
            "mongodb+srv://admin:12345@springmongocluster.xbfbl9p.mongodb.net/?retryWrites=true&w=majority";
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(connectionString);
    }
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient){
        return new MongoTemplate(mongoClient(), AppConfig.dataBaseName);
    }
}

