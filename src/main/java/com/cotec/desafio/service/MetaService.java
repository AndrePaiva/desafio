package com.cotec.desafio.service;

import com.cotec.desafio.model.Meta;
import com.cotec.desafio.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaService implements BasicCrudService<Meta> {

    @Autowired
    private MetaRepository metaRepository;

    @Override
    public Meta save(Meta meta) {
        return metaRepository.save(meta);
    }

    @Override
    public Meta findById(Long id) {
        return metaRepository.findOne(id);
    }

    @Override
    public boolean remove(Long id) {
        metaRepository.delete(id);
        return true;
    }

}
