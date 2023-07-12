package com.sms.ReactiveJavaDriver.service;

import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.sms.ReactiveJavaDriver.model.Entity;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.sms.ReactiveJavaDriver.service.SubscriberHelpers.ObservableSubscriber;
import com.sms.ReactiveJavaDriver.service.SubscriberHelpers.OperationSubscriber;
import reactor.core.publisher.Mono;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.mod;

@Service
public class EntityService {
    private final MongoCollection<Entity> mongoCollection;

    @Autowired
    public EntityService(MongoCollection<Entity> mongoCollection){
        this.mongoCollection = mongoCollection;
    }

    public Mono<Entity> addEntity(Entity entity){
        ObservableSubscriber<InsertOneResult> insertOneSubscriber = new OperationSubscriber<>();
        mongoCollection.insertOne(entity).subscribe(insertOneSubscriber);
        insertOneSubscriber.await();
        return Mono.just(entity);
    }

    public Flux<Entity> addManyEntities(List<Entity> entityCollection) {
        ObservableSubscriber<InsertManyResult> insertManySubscriber = new OperationSubscriber<>();
        mongoCollection.insertMany(entityCollection).subscribe(insertManySubscriber);
        insertManySubscriber.await();
        return Flux.fromIterable(entityCollection);
    }

    public FindPublisher<Entity> getAllEntities() {
        return mongoCollection.find();
    }

    public Publisher<Entity> getEntity(String id){
        return mongoCollection.find(eq("_id",id)).first();
    }

    public Publisher<Entity> removeEntity(String id) {
        return mongoCollection.findOneAndDelete(eq("_id",id));
    }

    public Publisher<Entity> updateEntity(Entity entity) {
        return mongoCollection.findOneAndReplace(eq("_id",entity.getId()),entity);
    }

    public Flux<Entity> updateManyEntities(Collection<Entity> entityCollection) {
        return Flux.fromIterable(entityCollection)
                .flatMap(this::updateEntity)
                .filter(Objects::nonNull);
    }

    public FindPublisher<Entity> getEntitiesByQuery() {
        return mongoCollection.find(eq("field1",null));
    }
}
