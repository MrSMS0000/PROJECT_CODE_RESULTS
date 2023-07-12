package com.sms.ReactiveAPI.service;

import com.sms.ReactiveAPI.configuration.AppConfig;
import com.sms.ReactiveAPI.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class EntityService {
    private final ReactiveMongoTemplate template;

    @Autowired
    public EntityService(ReactiveMongoTemplate template){
        this.template = template;
    }

//---------------------INSERT ONE--------------------

    // insert() -> id already exist -> error
//    public Mono<Entity> addEntity(Entity entity){
//        return template.insert(entity, AppConfig.collectionName);
//    }

    // save() -> id already exist -> update
    public Mono<Entity> addEntity(Entity entity){
        return template.save(entity, AppConfig.collectionName);
    }

// ----------------------INSERT MANY-------------------------

    // insert(Collection) - any duplicate id -> error, no further insert
    public Flux<Entity> addManyEntities(Collection<Entity> entityCollection){
        return template.insert(entityCollection, AppConfig.collectionName);
    }

    // for loop insert(Entity) - if id does not exist then only insert one by one
//    public Flux<Entity> addManyEntities(Collection<Entity> entityCollection) {
//        return Flux.fromIterable(entityCollection)
//                .flatMap(entity -> {
//                    return template.findById(entity.getId(),Entity.class,AppConfig.collectionName)
//                            .switchIfEmpty(Mono.defer(() -> {
//                                return template.insert(entity,AppConfig.collectionName);
//                            }));
//                });
//    }

// -------------------------FIND ALL---------------------------------
    public Flux<Entity> getAllEntities() {
        return template.findAll(Entity.class, AppConfig.collectionName);
    }

// -------------------------FIND BY ID-------------------------

    public Mono<Entity> getEntity(String id){
        return template.findById(id, Entity.class, AppConfig.collectionName);
    }

//-------------------------DELETE BY ID------------------------

    // findAndRemove() -> if id does not present -> null
    public Mono<Entity> removeEntity(String id){
        return template.findAndRemove(query(where("id").is(id)), Entity.class, AppConfig.collectionName);
    }

    // findByID + remove
//    public Mono<Entity> removeEntity(String id){
//        return template.findById(id, Entity.class, AppConfig.collectionName)
//                .doOnNext(entity -> {
//                    template.remove(entity, AppConfig.collectionName).subscribe();
//                });
//    }

//---------------------------UPDATE ONE-----------------------

    // findAndReplace() -> id not present -> null
    public Mono<Entity> updateEntity(Entity entity){
        return template.findAndReplace(
                query(where("id").is(entity.getId())),
                entity,
                new FindAndReplaceOptions(),
                Entity.class,
                AppConfig.collectionName
        );
    }

    // save() -> id not present -> insert
//    public Mono<Entity> updateEntity(Entity entity){
//        return template.save(entity, AppConfig.collectionName);
//    }

//---------------------------UPDATE MANY----------------------

    // no inbuilt function - updating one by one asynchronously
    public Flux<Entity> updateManyEntities(Collection<Entity> entityCollection){
        return Flux.fromIterable(entityCollection)
                .flatMap(this::updateEntity)
                .filter(Objects::nonNull);
    }

//------------------------Simple Fetch Query------------------------
    public Flux<Entity> getEntitiesByQuery(){
        return template.find(query(where("field1").isNull()), Entity.class, AppConfig.collectionName);
    }

}
