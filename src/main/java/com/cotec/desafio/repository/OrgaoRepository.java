package com.cotec.desafio.repository;

import com.cotec.desafio.model.Orgao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgaoRepository extends PagingAndSortingRepository<Orgao, Long> {
}
