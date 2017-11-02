package com.cotec.desafio.service;

import com.cotec.desafio.model.Meta;
import com.cotec.desafio.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaService implements BasicCrudService<Meta> {

    @Autowired
    private MetaRepository metaRepository;


}
