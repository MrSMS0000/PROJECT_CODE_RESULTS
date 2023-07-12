package com.sms.ReactiveAPI.controller;

import com.sms.ReactiveAPI.model.Entity;
import com.sms.ReactiveAPI.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

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
    public Flux<Entity> addManyEntities(@RequestBody Collection<Entity> entityCollection){
        return entityService.addManyEntities(entityCollection);
    }

    @GetMapping
    public Flux<Entity> getAllEntities(){
        return entityService.getAllEntities();
    }

    @GetMapping("/{id}")
    public Mono<Entity> getEntity(@PathVariable String id){
        return entityService.getEntity(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Entity> removeEntity(@PathVariable String id){
        return entityService.removeEntity(id);
    }

    @PutMapping
    public Mono<Entity> updateEntity(@RequestBody Entity entity){
        return entityService.updateEntity(entity);
    }

    @PutMapping("/many")
    public Flux<Entity> updateManyEntities(@RequestBody Collection<Entity> entityCollection){
        return entityService.updateManyEntities(entityCollection);
    }

    @GetMapping("/query")
    public Flux<Entity> getEntitiesByQuery(){
        return entityService.getEntitiesByQuery();
    }

}