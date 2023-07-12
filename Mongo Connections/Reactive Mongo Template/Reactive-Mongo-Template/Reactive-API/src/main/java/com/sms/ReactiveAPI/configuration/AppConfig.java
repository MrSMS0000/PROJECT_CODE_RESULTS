package com.sms.ReactiveAPI.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class AppConfig {

    public static final String dataBaseName = "Testing";
    public static final String collectionName = "api";
    private static final String connectionString =
            //"mongodb://localhost:27017";
            "mongodb+srv://admin:12345@springmongocluster.xbfbl9p.mongodb.net/?retryWrites=true&w=majority";
    public static MongoClient mongoClient;
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(connectionString);
    }
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient){
        return new ReactiveMongoTemplate(mongoClient(), AppConfig.dataBaseName);
    }
}
