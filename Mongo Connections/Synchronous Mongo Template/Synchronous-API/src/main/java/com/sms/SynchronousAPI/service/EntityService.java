package com.sms.SynchronousAPI.service;

import com.sms.SynchronousAPI.configuration.AppConfig;
import com.sms.SynchronousAPI.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class EntityService {
    private final MongoTemplate mongoTemplate;

    // Assigning MongoTemplate instance in the constructor to initialize.
    @Autowired
    public EntityService(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
// -----------------------INSERT ONE-------------------------

    // insert() - duplicate id -> error
//    public Entity addEntity(Entity entity){
//        return mongoTemplate.insert(entity, AppConfig.collectionName);
//    }

    // save() - duplicate id -> update
    public Entity addEntity(Entity entity){
        return mongoTemplate.save(entity,AppConfig.collectionName);
    }

// ----------------------INSERT MANY-------------------------

    // insert(Collection) - any duplicate -> error, no further insert
    public Collection<Entity> addManyEntities(Collection<Entity> entityCollection){
        return mongoTemplate.insert(entityCollection,AppConfig.collectionName);
    }

    // for loop insert(Entity) - if id does not exist then only insert one by one
//    public Collection<Entity> addManyEntities(Collection<Entity> entityCollection){
//        ArrayList<Entity> answer = new ArrayList<>();
//        for(Entity entity:entityCollection)
//            if(mongoTemplate.findById(entity.getId(),Entity.class,AppConfig.collectionName)==null)
//                answer.add((Entity) mongoTemplate.insert(entity,AppConfig.collectionName));
//        return answer;
//    }

    // insert(collection) - remove duplicate ids first
//    public Collection<Entity> addManyEntities(Collection<Entity> entityCollection){
//        Set<Entity> entitySet = new HashSet<>(entityCollection);      // making set to remove duplicates
//        Set<String> ids = entitySet.stream().map(Entity::getId).collect(toSet());     // extracting ids from entities
//        Set<String> finalIds = mongoTemplate.query(Entity.class)
//                .matching(Criteria.where("id").in(ids))               // finding existing ids entities
//                .all().stream()
//                .map(Entity::getId).collect(Collectors.toSet());      // extracting ids from entities
//        List<Entity> finalEntities = entitySet.stream()
//        .filter(entity -> !finalIds.contains(entity.getId()))         // filtering unique entities
//        .collect(Collectors.toList());
//
//        return mongoTemplate.insert(finalEntities, AppConfig.collectionName);     // inserting unique entities
//    }

// -------------------------FIND ALL-------------------------

    public Collection<Entity> getAllEntities(){
        return mongoTemplate.findAll(Entity.class,AppConfig.collectionName);
    }

// -------------------------FIND BY ID-------------------------

    // findById - id doesn't exist -> null
    public Entity getEntity(String id){
        return mongoTemplate.findById(id, Entity.class, AppConfig.collectionName);
    }

//-------------------------DELETE BY ID------------------------

    // findByID + remove(object) - id doesn't exist -> error
//    public Entity removeEntity(String id){
//        Entity entity = mongoTemplate.findById(id,Entity.class, AppConfig.collectionName);
//        if(entity!=null)
//            mongoTemplate.remove(entity, AppConfig.collectionName);
//        return entity;
//    }

    // findByID + remove(object) - id doesn't exist -> null
//    public Entity removeEntity(String id){
//        Entity entity = mongoTemplate.findById(id,Entity.class, AppConfig.collectionName);
//        if(entity!=null)
//            mongoTemplate.remove(entity, AppConfig.collectionName);
//        return entity;
//    }

    // findAndRemove(Query) - id doesn't exist -> null
    public Entity removeEntity(String id){
        return mongoTemplate.findAndRemove(Query.query(where("id").is(id)), Entity.class,AppConfig.collectionName);
    }


//---------------------------UPDATE ONE-----------------------

    // FindAndReplace() - id not present -> null, no insert
    // returns old doc, can change to new, upsert, ...
    public Entity updateEntity(Entity entity) {
        return mongoTemplate.findAndReplace(
                Query.query(where("id").is(entity.getId())),
                entity,
                new FindAndReplaceOptions(),
                Entity.class,
                AppConfig.collectionName);
    }

    // Save() - id not present -> insert
    // returns new doc
//    public Entity updateEntity(Entity entity){
//        return mongoTemplate.save(entity,AppConfig.collectionName);
//    }

//---------------------------UPDATE MANY----------------------

    // no any function available in mongoTemplate
    // have to use mongoRepository if it has higher performance

    // for loop on updateSingleEntity
    public Collection<Entity> updateManyEntities(Collection<Entity> entityCollection){
        ArrayList<Entity> returnList = new ArrayList<>();
        for(Entity entity:entityCollection) {
            Entity returnEntity = updateEntity(entity);
            if(returnEntity!=null)
                returnList.add(returnEntity);
        }
        return returnList;
    }

//--------------------------FIND BY QUERY-------------------------

    public Collection<Entity> getEntitiesByQuery(){
        return mongoTemplate.find(query(where("field1").isNull()), Entity.class, AppConfig.collectionName);
    }

}