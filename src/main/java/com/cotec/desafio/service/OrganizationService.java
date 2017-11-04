package com.cotec.desafio.service;

import com.cotec.desafio.model.Organization;
import com.cotec.desafio.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService implements BasicCrudService<Organization> {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization findById(Long id) {
        return organizationRepository.findOne(id);
    }

    @Override
    public List<Organization> find(Pageable pageable) {
        return organizationRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Organization> find() {
        return (List<Organization>) organizationRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        organizationRepository.delete(id);
        return true;
    }

    @Override
    public boolean removeBatch(List<Organization> organizations) {
        organizationRepository.delete(organizations);
        return true;
    }
}
