package com.cotec.desafio.service.integration;

import com.cotec.desafio.model.Organization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private Organization organization;
    private Organization responseOrganization;

    /**
     * O cenário deste teste é a criação de um Órgão.
     */
    @Test
    public void itShouldCreateOrganization(){
        givenAnOrganization();
        whenTryToCreateOrganization();
        thenShouldCreateOrganization();
    }

    /**
     * O cenário deste teste é a criação e edição de um Órgão.
     */
    @Test
    public void itShouldUpdateOrganization(){
        givenAnExistingOrganization();
        whenTryToUpdateOrganization();
        thenShouldUpdateOrganization();
    }

    private void givenAnExistingOrganization() {
        givenAnOrganization();
        whenTryToCreateOrganization();
    }

    private void givenAnOrganization() {
        organization = new Organization();
        organization.setDescription("OrganizationTest");
    }

    private void whenTryToCreateOrganization() {
        ResponseEntity<Organization> responseEntity =
                restTemplate.postForEntity("/organization", organization, Organization.class);
        responseOrganization = responseEntity.getBody();
    }

    private void whenTryToUpdateOrganization() {
        organization = responseOrganization;
        organization.setDescription("UpdatedOrganization");

        restTemplate.put("/organization", organization);

        ResponseEntity<Organization> responseEntity = restTemplate.getForEntity("/organization/" + organization.getId(), Organization.class);
        responseOrganization = responseEntity.getBody();
    }

    private void thenShouldCreateOrganization() {
        Assert.assertNotNull(responseOrganization.getId());
        Assert.assertEquals(organization.getDescription(), responseOrganization.getDescription());
    }

    private void thenShouldUpdateOrganization() {
        Assert.assertEquals(organization.getId(), responseOrganization.getId());
        Assert.assertEquals("UpdatedOrganization", responseOrganization.getDescription());

    }
}
