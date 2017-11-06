package com.cotec.desafio.service.integration;

import com.cotec.desafio.model.Sector;
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
public class SectorServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private Sector sector;

    private Sector responseSector;

    /**
     * O cenário deste teste é a criação de um Setor.
     */
    @Test
    public void itShouldCreateSector(){
        givenASector();
        whenTryToCreateSector();
        thenShouldCreateSector();
    }

    /**
     * O cenário deste teste é a criação de um Setor e editar a descrição dele.
     */
    @Test
    public void itShouldEditSector(){
        givenAnExistingSector();
        whenTryToUpdateSector();
        thenShouldUpdateSector();
    }

    private void givenASector() {
        sector = new Sector();
        sector.setDescription("SectorTest");
    }

    private void givenAnExistingSector() {
        givenASector();
        whenTryToCreateSector();
    }

    private void whenTryToCreateSector() {
        ResponseEntity<Sector> responseEntity =
                restTemplate.postForEntity("/sector", sector, Sector.class);
        responseSector = responseEntity.getBody();
    }

    private void whenTryToUpdateSector() {
        sector = responseSector;
        sector.setDescription("UpdatedSector");
        restTemplate.put("/sector", sector);
        ResponseEntity<Sector> responseEntity = restTemplate.getForEntity("/sector/" + sector.getId(), Sector.class);
        responseSector = responseEntity.getBody();
    }

    private void thenShouldCreateSector() {
        Assert.assertNotNull(responseSector.getId());
        Assert.assertEquals(sector.getDescription(), responseSector.getDescription());
    }

    private void thenShouldUpdateSector() {
        Assert.assertEquals("UpdatedSector", responseSector.getDescription());
    }
}
