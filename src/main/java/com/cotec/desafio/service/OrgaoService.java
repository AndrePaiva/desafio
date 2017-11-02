package com.cotec.desafio.service;

import com.cotec.desafio.model.Orgao;
import com.cotec.desafio.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgaoService implements BasicCrudService<Orgao> {

    @Autowired
    private OrgaoRepository orgaoRepository;

}
