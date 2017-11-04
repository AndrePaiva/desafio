package com.cotec.desafio.repository;

import com.cotec.desafio.model.Organization;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {
}
