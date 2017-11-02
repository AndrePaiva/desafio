package com.cotec.desafio.repository;

import com.cotec.desafio.model.Setor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends PagingAndSortingRepository<Setor, Long> {

}
