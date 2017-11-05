package com.cotec.desafio.controller;

import com.cotec.desafio.exception.InvalidEntityException;
import com.cotec.desafio.model.BasicEntity;
import com.cotec.desafio.service.BasicCrudService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BasicRestController<T extends BasicEntity, S extends BasicCrudService<T, ?>> {

    protected S service;

    protected BasicRestController(S service) {
        this.service = service;
    }

    @PostMapping
    public T create(@RequestBody T entity) throws InvalidEntityException {
        return service.save(entity);
    }

    @PutMapping
    public T update(@RequestBody T entity) throws InvalidEntityException {
        return service.save(entity);
    }

    @GetMapping("/{id}")
    public T findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<T> find(@RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit) {
        if (offset != null && limit != null) {
            return service.find(new PageRequest(offset, limit));
        } else {
            return service.find();
        }
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return service.remove(id);
    }

    @PostMapping("/delete/batch")
    public boolean removeBatch(@RequestBody List<T> list) {
        return service.removeBatch(list);
    }

}
