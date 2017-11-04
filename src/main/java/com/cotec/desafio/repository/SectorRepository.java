package com.cotec.desafio.repository;

import com.cotec.desafio.model.Sector;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends PagingAndSortingRepository<Sector, Long> {

    Sector findByDescription(@Param("description") String description);

}
