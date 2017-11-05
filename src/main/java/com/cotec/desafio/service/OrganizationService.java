package com.cotec.desafio.service;

import com.cotec.desafio.model.Organization;
import com.cotec.desafio.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService extends BasicCrudService<Organization, OrganizationRepository> {

    @Autowired
    protected OrganizationService(OrganizationRepository repository) {
        super(repository);
    }

}
