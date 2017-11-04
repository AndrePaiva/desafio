package com.cotec.desafio.controller;

import com.cotec.desafio.model.Sector;
import com.cotec.desafio.service.BasicCrudService;
import com.cotec.desafio.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sector")
public class SectorController extends BasicRestController<Sector> {

    @Autowired
    private SectorService sectorService;

    @Override
    BasicCrudService getService() {
        return sectorService;
    }

    @GetMapping("/ByDescription/{description}")
    public Sector findByDescription(@PathVariable String description) {
        return sectorService.findByDescription(description);
    }
}
