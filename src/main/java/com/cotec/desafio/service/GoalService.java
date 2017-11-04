package com.cotec.desafio.service;

import com.cotec.desafio.exception.InvalidEntityException;
import com.cotec.desafio.model.Goal;
import com.cotec.desafio.model.Organization;
import com.cotec.desafio.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Service
public class GoalService implements BasicCrudService<Goal> {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public Goal save(Goal goal) throws InvalidEntityException {
        validateOrganization(goal);
        validateGoalRelation(goal);
        return goalRepository.save(goal);
    }

    private void validateOrganization(Goal goal) throws InvalidEntityException {
        Organization org = goal.getOrganization();
        if (org == null) {
            throw new InvalidEntityException("Organization not informed.");
        }
        if (goal.getGoal() != null && !org.equals(goal.getGoal().getOrganization())) {
            throw new InvalidEntityException("Organization must be the same for all related goals.");
        }
    }

    private void validateGoalRelation(Goal goal) throws InvalidEntityException {
        Set<Goal> visited = new HashSet<>();
        visited.add(goal);
        Goal relatedGoal = goal.getGoal();

        while (relatedGoal != null) {
            if (visited.contains(relatedGoal)) {
                throw new InvalidEntityException(format("Cycle found for goal with id: %s.", relatedGoal.getId()));
            }
            visited.add(relatedGoal);
            relatedGoal = relatedGoal.getGoal();
        }
    }

    @Override
    public Goal findById(Long id) {
        return goalRepository.findOne(id);
    }

    @Override
    public List<Goal> find(Pageable pageable) {
        return goalRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Goal> find() {
        return (List<Goal>) goalRepository.findAll();
    }

    public List<Goal> findAllByOrganizationId(Long id) {
        return goalRepository.findAllByOrganizationId(id);
    }

    @Override
    public boolean remove(Long id) {
        goalRepository.delete(id);
        return true;
    }

    @Override
    public boolean removeBatch(List<Goal> goals) {
        goalRepository.delete(goals);
        return true;
    }
}
