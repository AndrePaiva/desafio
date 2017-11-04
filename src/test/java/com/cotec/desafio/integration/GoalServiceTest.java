package com.cotec.desafio.integration;


import com.cotec.desafio.model.Goal;
import com.cotec.desafio.model.Organization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoalServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;


    private Organization organization1;
    private Organization organization2;
    private Goal goal1;
    private Goal goal2;
    private HttpStatus response;

    private void givenAnOrganization() {
        organization1 = new Organization();
        organization1.setDescription("Organization1");

        ResponseEntity<Organization> responseEntity =
                restTemplate.postForEntity("/organization", organization1, Organization.class);
        organization1 = responseEntity.getBody();
    }

    @Test
    public void itShouldCreateGoal(){
        givenAnOrganization();
        whenTryToCreateGoal();
        thenShouldCreateGoal();
    }

    @Test
    public void itShouldCreateRelatedGoal(){
        givenAnOrganization();
        whenTryToCreateRelatedGoal(organization1);
        thenShouldCreateRelatedGoal();
    }

    @Test
    public void itShouldNotCreateRelateInvalidGoals(){
        givenAnOrganization();
        givenOtherOrganization();
        whenTryToCreateRelatedGoal(organization2);
        thenShouldReturnError();
    }

    private void thenShouldReturnError() {
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }

    private void thenShouldCreateRelatedGoal() {
        Assert.assertNotNull(goal2);
        Assert.assertEquals(goal1.getOrganization(), goal2.getOrganization());
    }

    private void givenOtherOrganization() {
        organization2 = new Organization();
        organization2.setDescription("Organization2");

        ResponseEntity<Organization> responseEntity =
                restTemplate.postForEntity("/organization", organization2, Organization.class);
        organization2 = responseEntity.getBody();
    }

    private void thenShouldCreateGoal() {
        Assert.assertNotNull(goal1);
        Assert.assertEquals(goal1.getOrganization(), organization1);
    }

    private void whenTryToCreateGoal() {
        goal1 = new Goal();
        goal1.setDescription("GoalTest");
        goal1.setOrganization(organization1);

        ResponseEntity<Goal> responseEntity =
                restTemplate.postForEntity("/goal", goal1, Goal.class);
        goal1 = responseEntity.getBody();
    }

    private void whenTryToCreateRelatedGoal(Organization organization) {
        whenTryToCreateGoal();

        goal2 = new Goal();
        goal2.setDescription("GoalTest2");
        goal2.setOrganization(organization);
        goal2.setGoal(goal1);

        ResponseEntity<Goal> responseEntity =
                restTemplate.postForEntity("/goal", goal2, Goal.class);
        response = responseEntity.getStatusCode();
    }
}
