package com.cotec.desafio.controller;

import com.cotec.desafio.model.Organization;
import com.cotec.desafio.service.BasicCrudService;
import com.cotec.desafio.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/organization")
public class OrganizationController extends BasicRestController<Organization> {

    @Autowired
    private OrganizationService organizationService;

    @Override
    BasicCrudService getService() {
        return organizationService;
    }
}
