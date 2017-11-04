package com.cotec.desafio.controller;

import com.cotec.desafio.model.Goal;
import com.cotec.desafio.service.BasicCrudService;
import com.cotec.desafio.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/goal")
public class GoalController extends BasicRestController<Goal> {

    @Autowired
    private GoalService goalService;

    @Override
    protected BasicCrudService getService() {
        return goalService;
    }

    @GetMapping
    @RequestMapping(value = "/allByOrganizationId/{id}")
    public List<Goal> findAllByOrganizationId(@PathVariable Long id) {
        return goalService.findAllByOrganizationId(id);
    }
}
