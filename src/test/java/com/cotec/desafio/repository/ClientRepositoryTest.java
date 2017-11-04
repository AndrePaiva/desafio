package com.cotec.desafio.repository;

import com.cotec.desafio.model.Goal;
import com.cotec.desafio.model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GoalRepository goalRepository;

    private Goal givenAGoal() {
        Goal goal = new Goal();
        goal.setDescription("Reduzir custos");
        return goal;
    }

    private Goal givenAnExistingOrganizationAndGoal() {
        Organization organization = givenAnExistingOrganization();
        Goal goal = givenAGoal();
        goal.setOrganization(organization);
        entityManager.persist(goal);
        return goal;
    }

    private Organization givenAnExistingOrganization() {
        Organization organization = givenAnOrganization();
        entityManager.persist(organization);
        return organization;
    }

    private Organization givenAnOrganization() {
        Organization organization = new Organization();
        organization.setDescription("SEFIND");
        return organization;
    }

    @Test
    public void testFindById() {
        Goal goal = givenAnExistingOrganizationAndGoal();
        Goal goalFromDb = goalRepository.findOne(goal.getId());
        assertEquals(goal.getDescription(), goalFromDb.getDescription());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCreateWithoutOrganizationShouldThrowException() {
        Goal goal = givenAGoal();
        entityManager.persist(goal);
    }
}