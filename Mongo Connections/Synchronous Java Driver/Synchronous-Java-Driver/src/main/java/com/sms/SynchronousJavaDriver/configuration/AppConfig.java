package com.sms.SynchronousJavaDriver.configuration;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sms.SynchronousJavaDriver.model.Entity;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
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
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        return MongoClients.create(AppConfig.connectionString)
                .getDatabase(AppConfig.dataBaseName)
                .withCodecRegistry(pojoCodecRegistry)
                .getCollection(AppConfig.collectionName, Entity.class);
    }
}
