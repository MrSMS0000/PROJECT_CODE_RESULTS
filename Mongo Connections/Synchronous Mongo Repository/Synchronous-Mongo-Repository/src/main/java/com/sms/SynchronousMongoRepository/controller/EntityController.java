package com.sms.SynchronousMongoRepository.controller;

import com.sms.SynchronousMongoRepository.model.Entity;
import com.sms.SynchronousMongoRepository.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Entity addEntity(@RequestBody Entity entity){
        return entityService.addEntity(entity);
    }

    @PostMapping("/many")
    public List<Entity> addManyEntities(@RequestBody List<Entity> entityCollection){
        return entityService.addManyEntities(entityCollection);
    }

    @GetMapping
    public Collection<Entity> getAllEntities(){
        return entityService.getAllEntities();
    }

    @GetMapping("/{id}")
    public Entity getEntity(@PathVariable String id){
        return entityService.getEntity(id);
    }

    @DeleteMapping("/{id}")
    public Entity removeEntity(@PathVariable String id){
        return entityService.removeEntity(id);
    }

    @PutMapping
    public Entity updateEntity(@RequestBody Entity entity){
        return entityService.updateEntity(entity);
    }

    @PutMapping("/many")
    public Collection<Entity> updateManyEntities(@RequestBody Collection<Entity> entityCollection){
        return entityService.updateManyEntities(entityCollection);
    }

    @GetMapping("/query")
    public Collection<Entity> getEntitiesByQuery(){
        return entityService.getEntitiesByQuery();
    }

}
