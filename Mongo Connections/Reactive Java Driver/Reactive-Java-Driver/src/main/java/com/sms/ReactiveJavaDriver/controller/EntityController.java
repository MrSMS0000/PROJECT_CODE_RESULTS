package com.sms.ReactiveJavaDriver.controller;

import com.mongodb.reactivestreams.client.FindPublisher;
import com.sms.ReactiveJavaDriver.model.Entity;
import com.sms.ReactiveJavaDriver.service.EntityService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/entities")
public class EntityController {

    private final EntityService entityService;

    @Autowired
    public EntityController(EntityService entityService){
        this.entityService = entityService;
    }

    @PostMapping
    public Mono<Entity> addEntity(@RequestBody Entity entity){
        return entityService.addEntity(entity);
    }

    @PostMapping("/many")
    public Flux<Entity> addManyEntities(@RequestBody List<Entity> entityCollection){
        return entityService.addManyEntities(entityCollection);
    }

    @GetMapping
    public FindPublisher<Entity> getAllEntities(){
        return entityService.getAllEntities();
    }

    @GetMapping("/{id}")
    public Publisher<Entity> getEntity(@PathVariable String id){
        return entityService.getEntity(id);
    }

    @DeleteMapping("/{id}")
    public Publisher<Entity> removeEntity(@PathVariable String id){
        return entityService.removeEntity(id);
    }

    @PutMapping
    public Publisher<Entity> updateEntity(@RequestBody Entity entity){
        return entityService.updateEntity(entity);
    }

    @PutMapping("/many")
    public Flux<Entity> updateManyEntities(@RequestBody Collection<Entity> entityCollection){
        return entityService.updateManyEntities(entityCollection);
    }

    @GetMapping("/query")
    public FindPublisher<Entity> getEntitiesByQuery(){
        return entityService.getEntitiesByQuery();
    }

}
