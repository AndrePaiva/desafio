package com.cotec.desafio.service;

import com.cotec.desafio.model.Sector;
import com.cotec.desafio.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService implements BasicCrudService<Sector> {

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }

    @Override
    public Sector findById(Long id) {
        return sectorRepository.findOne(id);
    }

    @Override
    public List<Sector> find(Pageable pageable) {
        return sectorRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Sector> find() {
        return (List<Sector>) sectorRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        sectorRepository.delete(id);
        return true;
    }

    @Override
    public boolean removeBatch(List<Sector> sectors) {
        sectorRepository.delete(sectors);
        return true;
    }

    public Sector findByDescription(String description) {
        return sectorRepository.findByDescription(description);
    }

}
