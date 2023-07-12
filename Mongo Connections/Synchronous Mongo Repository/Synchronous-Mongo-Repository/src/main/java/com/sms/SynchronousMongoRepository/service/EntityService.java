package com.sms.SynchronousMongoRepository.service;

import com.sms.SynchronousMongoRepository.model.Entity;
import com.sms.SynchronousMongoRepository.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.mod;

@Service
public class EntityService {
    private final EntityRepository entityRepository;

    // Assigning MongoTemplate instance in the constructor to initialize.
    @Autowired
    public EntityService(EntityRepository entityRepository){
        this.entityRepository = entityRepository;
    }

    public Entity addEntity(Entity entity){
        return entityRepository.insert(entity);
    }

    public List<Entity> addManyEntities(List<Entity> entityCollection) {
        return entityRepository.save(entityCollection);
    }

    public Collection<Entity> getAllEntities() {
        return entityRepository.findAll();
    }

    public Entity getEntity(String id){
        return entityRepository.findById(id).orElse(null);
    }

    public Entity removeEntity(String id) {
        Entity entity = entityRepository.findById(id).orElse(null);
        if(entity!=null)
            entityRepository.delete(entity);
        return entity;
    }

    public Entity updateEntity(Entity entity) {
        Optional<Entity> entityOptional = entityRepository.findById(entity.getId());
        if(entityOptional.isPresent())
            entityRepository.save(entity);
        return entityOptional.orElse(null);
    }

    public Collection<Entity> updateManyEntities(Collection<Entity> entityCollection) {
        ArrayList<Entity> returnList = new ArrayList<>();
        for(Entity entity:entityCollection){
            Entity returnEntity = updateEntity(entity);
            if(returnEntity!=null)
                returnList.add(returnEntity);
        }
        return returnList;
    }

    public Collection<Entity> getEntitiesByQuery() {
        return entityRepository.findByQuery();
    }
}
