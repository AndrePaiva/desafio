package com.cotec.desafio.service;

import com.cotec.desafio.model.Orgao;
import com.cotec.desafio.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgaoService implements BasicCrudService<Orgao> {

    @Autowired
    private OrgaoRepository orgaoRepository;

    @Override
    public Orgao save(Orgao orgao) {
        return orgaoRepository.save(orgao);
    }

    @Override
    public Orgao findById(Long id) {
        return orgaoRepository.findOne(id);
    }

    @Override
    public boolean remove(Long id) {
        orgaoRepository.delete(id);
        return true;
    }

}
