package com.cotec.desafio.controller;

import com.cotec.desafio.service.BasicCrudService;
import org.springframework.web.bind.annotation.*;

public abstract class BasicRestController<T> {

    abstract BasicCrudService getService();

    @PostMapping
    @ResponseBody
    public T create(@RequestBody T entity) {
        return (T) getService().save(entity);
    }

    @PutMapping
    @ResponseBody
    public T update(@RequestBody T entity) {
        return (T) getService().save(entity);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public T findById(@PathVariable Long id) {
        return (T) getService().findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean remove(@PathVariable Long id) {
        return getService().remove(id);
    }

}
