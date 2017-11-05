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
public class GoalService extends BasicCrudService<Goal, GoalRepository> {

    @Autowired
    protected GoalService(GoalRepository repository) {
        super(repository);
    }

    @Override
    public Goal save(Goal entity) {
        validateOrganization(entity);
        validateGoalRelation(entity);
        return super.save(entity);
    }

    private void validateOrganization(Goal goal) throws InvalidEntityException {
        Organization org = goal.getOrganization();
        if (org == null) {
            throw new InvalidEntityException("Organization not informed.");
        }
        Goal relatedGoal = goal.getGoal();

        if (relatedGoal != null) {
            relatedGoal = repository.findOne(relatedGoal.getId());
            if (relatedGoal == null) {
                throw new InvalidEntityException(format("Related Goal not found for id: %s.", goal.getGoal().getId()));
            }
            if (!org.equals(relatedGoal.getOrganization())) {
                throw new InvalidEntityException("Organization must be the same for all related goals.");
            }
        }
    }

    private void validateGoalRelation(Goal goal) throws InvalidEntityException {
        Set<Goal> visited = new HashSet<>();
        visited.add(goal);
        Goal relatedGoal = goal.getGoal();

        while (relatedGoal != null) {
            relatedGoal = repository.findOne(relatedGoal.getId());
            if (visited.contains(relatedGoal)) {
                throw new InvalidEntityException(format("Cycle found for goal with id: %s.", relatedGoal.getId()));
            }
            visited.add(relatedGoal);
            relatedGoal = relatedGoal.getGoal();
        }
    }

    public List<Goal> findAllByOrganizationId(Long id) {
        return repository.findAllByOrganizationId(id);
    }

}
