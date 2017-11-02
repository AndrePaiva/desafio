package com.cotec.desafio.repository;

import com.cotec.desafio.model.Meta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends PagingAndSortingRepository<Meta, Long> {

}
