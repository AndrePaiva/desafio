package com.cotec.desafio.service;

import com.cotec.desafio.model.Setor;
import com.cotec.desafio.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetorService implements BasicCrudService<Setor> {

    @Autowired
    private SetorRepository setorRepository;

}
