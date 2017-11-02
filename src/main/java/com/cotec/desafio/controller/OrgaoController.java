package com.cotec.desafio.controller;

import com.cotec.desafio.model.Orgao;
import com.cotec.desafio.service.BasicCrudService;
import com.cotec.desafio.service.OrgaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orgao")
public class OrgaoController extends BasicRestController<Orgao> {

    @Autowired
    private OrgaoService orgaoService;

    @Override
    BasicCrudService getService() {
        return orgaoService;
    }
}
