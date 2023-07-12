package com.sms.ReactiveMongoRepository.repository;

import com.sms.ReactiveMongoRepository.model.Entity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EntityRepository extends ReactiveMongoRepository<Entity,String> {
    @Query("{ field1: null }")
    Flux<Entity> findByQuery();
}
