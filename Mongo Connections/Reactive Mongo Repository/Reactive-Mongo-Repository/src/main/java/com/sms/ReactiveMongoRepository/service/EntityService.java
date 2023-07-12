package com.sms.ReactiveMongoRepository.service;

import com.sms.ReactiveMongoRepository.model.Entity;
import com.sms.ReactiveMongoRepository.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Objects;

@Service
public class EntityService {
    private final EntityRepository entityRepository;

    @Autowired
    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Mono<Entity> addEntity(Entity entity) {
        return entityRepository.save(entity);
    }

    public Flux<Entity> addManyEntities(Collection<Entity> entityCollection) {
        return entityRepository.insert(entityCollection);
    }

    public Flux<Entity> getAllEntities() {
        return entityRepository.findAll();
    }

    public Mono<Entity> getEntity(String id) {
        return entityRepository.findById(id);
    }

    public Mono<Entity> removeEntity(String id) {
        return entityRepository.findById(id).flatMap(entity -> entityRepository.delete(entity).thenReturn(entity));
    }

    public Mono<Entity> updateEntity(Entity entity) {
        return entityRepository.findById(entity.getId())
                .flatMap(oldEntity -> entityRepository.save(entity).thenReturn(oldEntity));
    }

    public Flux<Entity> updateManyEntities(Collection<Entity> entityCollection) {
        return Flux.fromIterable(entityCollection)
                .flatMap(entity -> entityRepository.findById(entity.getId())
                        .flatMap(oldEntity -> entityRepository.save(entity).thenReturn(oldEntity)));
    }

    public Flux<Entity> getEntitiesByQuery() {
        return entityRepository.findByQuery();
    }

}
