package com.cotec.desafio.controller;

import com.cotec.desafio.model.Organization;
import com.cotec.desafio.service.BasicCrudService;
import com.cotec.desafio.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController extends BasicRestController<Organization, OrganizationService> {

    @Autowired
    public OrganizationController(OrganizationService service) {
        super(service);
    }

}
