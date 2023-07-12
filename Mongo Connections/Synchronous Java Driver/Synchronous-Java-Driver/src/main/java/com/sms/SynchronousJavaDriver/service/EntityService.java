package com.sms.SynchronousJavaDriver.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.sms.SynchronousJavaDriver.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.mod;

@Service
public class EntityService {
    private final MongoCollection<Entity> mongoCollection;

    @Autowired
    public EntityService(MongoCollection<Entity> mongoCollection){
        this.mongoCollection = mongoCollection;
    }

    public Entity addEntity(Entity entity){
        mongoCollection.insertOne(entity);
        return entity;
    }

    public List<Entity> addManyEntities(List<Entity> entityCollection) {
        mongoCollection.insertMany(entityCollection);
        return entityCollection;
    }

    public Collection<Entity> getAllEntities() {
        return mongoCollection.find().into(new ArrayList<>());
    }

    public Entity getEntity(String id){
        return mongoCollection.find(eq("_id",id)).first();
    }

    public Entity removeEntity(String id) {
        return mongoCollection.findOneAndDelete(eq("_id",id));
    }

    public Entity updateEntity(Entity entity) {
        return mongoCollection.findOneAndReplace(eq("_id",entity.getId()),entity);
    }

    public Collection<Entity> updateManyEntities(Collection<Entity> entityCollection) {
        ArrayList<Entity> returnList = new ArrayList<>();
        for(Entity entity:entityCollection){
            Entity returnEntity = updateEntity(entity);
            if(returnEntity!=null)
                returnList.add(entity);
        }
        return returnList;
    }

    public Collection<Entity> getEntitiesByQuery() {
        return mongoCollection.find(eq("field1",null)).into(new ArrayList<>());
    }
}
