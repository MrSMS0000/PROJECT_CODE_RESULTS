package com.sms.SynchronousMongoRepository.repository;

import com.sms.SynchronousMongoRepository.model.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;

public interface EntityRepository extends MongoRepository<Entity,String> {
    @Query("{ field1: null }")
    Collection<Entity> findByQuery();
}
