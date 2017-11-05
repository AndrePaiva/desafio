package com.cotec.desafio.service;

import com.cotec.desafio.model.Sector;
import com.cotec.desafio.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorService extends BasicCrudService<Sector, SectorRepository> {

    @Autowired
    protected SectorService(SectorRepository repository) {
        super(repository);
    }

    public Sector findByDescription(String description) {
        return repository.findByDescription(description);
    }

}
