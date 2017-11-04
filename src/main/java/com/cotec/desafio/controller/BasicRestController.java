package com.cotec.desafio.controller;

import com.cotec.desafio.exception.InvalidEntityException;
import com.cotec.desafio.model.BasicEntity;
import com.cotec.desafio.service.BasicCrudService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BasicRestController<T extends BasicEntity> {

    abstract BasicCrudService getService();

    @PostMapping
    @ResponseBody
    public T create(@RequestBody T entity) throws InvalidEntityException {
        return (T) getService().save(entity);
    }

    @PutMapping
    @ResponseBody
    public T update(@RequestBody T entity) throws InvalidEntityException {
        return (T) getService().save(entity);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public T findById(@PathVariable Long id) {
        return (T) getService().findById(id);
    }

    @GetMapping
    @ResponseBody
    public List<T> find(@RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit) {
        if (offset != null && limit != null){
            return (List<T>) getService().find(new PageRequest(offset, limit));
        } else {
            return getService().find();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean remove(@PathVariable Long id) {
        return getService().remove(id);
    }

    @PostMapping("/batchDelete")
    @ResponseBody
    public boolean removeBatch(@RequestBody List<T> list) {
        return getService().removeBatch(list);
    }

}
