package com.cotec.desafio.service;

public interface BasicCrudService<T> {

    T save(T entity);

    T findById(Long id);

    boolean remove(Long id);
}
