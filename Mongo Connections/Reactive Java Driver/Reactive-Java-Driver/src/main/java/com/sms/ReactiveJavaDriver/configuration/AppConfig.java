package com.sms.ReactiveJavaDriver.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.sms.ReactiveJavaDriver.model.Entity;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class AppConfig {
    public static final String dataBaseName = "Testing";
    public static final String collectionName = "api";
    public static final String connectionString =
            //"mongodb://localhost:27017";
            "mongodb+srv://admin:12345@springmongocluster.xbfbl9p.mongodb.net/?retryWrites=true&w=majority";

    @Bean
    public MongoCollection<Entity> mongoCollection(){
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        return MongoClients.create(connectionString)
                .getDatabase(AppConfig.dataBaseName)
                .withCodecRegistry(pojoCodecRegistry)
                .getCollection(AppConfig.collectionName, Entity.class);
    }
}
