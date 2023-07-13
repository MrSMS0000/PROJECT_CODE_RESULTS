package com.sms.Synchronous.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class AppConfig {

    public static final String dataBaseName = "project";
    private static final String connectionString =
            //"mongodb://localhost:27017";
            "mongodb+srv://admin:12345@springmongocluster.xbfbl9p.mongodb.net/?retryWrites=true&w=majority";
    public static MongoClient mongoClient;
    public static MongoDatabase mongoDatabase;
    @Bean
    public MongoClient mongoClient(){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToConnectionPoolSettings(builder -> builder.maxSize(200))
                .applyConnectionString(new ConnectionString(connectionString))
                .codecRegistry(pojoCodecRegistry)
                .build();
        mongoClient = MongoClients.create(settings);
        mongoDatabase = mongoClient.getDatabase(AppConfig.dataBaseName).withCodecRegistry(pojoCodecRegistry);
        return mongoClient;
    }
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient){
        return new MongoTemplate(mongoClient(), AppConfig.dataBaseName);
    }
}
