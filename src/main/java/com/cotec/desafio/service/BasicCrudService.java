package com.cotec.desafio.service;

import com.cotec.desafio.exception.InvalidEntityException;
import com.cotec.desafio.model.BasicEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BasicCrudService<T extends BasicEntity> {

    T save(T entity) throws InvalidEntityException;

    T findById(Long id);

    List<T> find(Pageable pageable);

    List<T> find();

    boolean remove(Long id);

    boolean removeBatch(List<T> list);

}
