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
@RequestMapping("/sector")
public class SectorController extends BasicRestController<Sector, SectorService> {

    @Autowired
    public SectorController(SectorService service) {
        super(service);
    }

    @GetMapping("/description/{description}")
    public Sector findByDescription(@PathVariable String description) {
        return service.findByDescription(description);
    }
}
