package com.cotec.desafio.controller;

import com.cotec.desafio.model.Setor;
import com.cotec.desafio.service.BasicCrudService;
import com.cotec.desafio.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/setor")
public class SetorController extends BasicRestController<Setor> {

    @Autowired
    private SetorService setorService;

    @Override
    BasicCrudService getService() {
        return setorService;
    }
}
