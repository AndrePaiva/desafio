package com.cotec.desafio.controller;

import com.cotec.desafio.model.Meta;
import com.cotec.desafio.service.BasicCrudService;
import com.cotec.desafio.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/meta")
public class MetaController extends BasicRestController<Meta> {

    @Autowired
    private MetaService metaService;

    @Override
    protected BasicCrudService getService() {
        return metaService;
    }
}
