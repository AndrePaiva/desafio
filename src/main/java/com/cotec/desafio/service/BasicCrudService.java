package com.cotec.desafio.service;

import com.cotec.desafio.model.BasicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public abstract class BasicCrudService<T extends BasicEntity, R extends PagingAndSortingRepository<T, Long>> {

    protected R repository;

    protected BasicCrudService(R repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(Long id) {
        return repository.findOne(id);
    }

    public T findById(T entity) {
        return findById(entity.getId());
    }

    public List<T> find(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public List<T> find() {
        return (List<T>) repository.findAll();
    }

    public boolean remove(Long id) {
        repository.delete(id);
        return true;
    }

    public boolean removeBatch(List<T> entities) {
        repository.delete(entities);
        return true;
    }

}
