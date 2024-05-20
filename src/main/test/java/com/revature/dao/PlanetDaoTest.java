package com.revature.dao;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;
import com.revature.utilities.ConnectionUtil;
import io.cucumber.java.ja.但し;
import io.javalin.Javalin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanetDaoTest {

    public Connection connection;
    public Javalin app;
    @BeforeEach
    public void setUp() throws SQLException {
        connection = ConnectionUtil.createConnection();
  /*  app=Javalin.create();
        RequestMapper.setUpEndPoints(app);*/
    }

    @Test
    public void  createPlanetTestPositive(){
        Planet createPlanet = new Planet();
        createPlanet.setId(1);
        createPlanet.setName("venus");
        createPlanet.setOwnerId(1);
        PlanetDao planetDao = new PlanetDao();
        Planet expectedPlanet = new Planet();
        expectedPlanet.setName("venus");
        expectedPlanet.setId(1);
        expectedPlanet.setOwnerId(createPlanet.getOwnerId());
        Planet actualPlanet = planetDao.createPlanet(createPlanet,createPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet,expectedPlanet);
    }
    @Test
    public void  createPlanetTestNegative(){
        Planet createPlanet = new Planet();
        createPlanet.setId(5);
        createPlanet.setName("");
        createPlanet.setOwnerId(1);
        PlanetDao planetDao = new PlanetDao();
        Planet expectedPlanet = new Planet();
        expectedPlanet.setId(5);
        expectedPlanet.setName("");
        expectedPlanet.setOwnerId(createPlanet.getOwnerId());
        Planet actualPlanet = planetDao.createPlanet(createPlanet,createPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet,expectedPlanet);
    }

    @Test
    public void  deletePlanetbyID(){
        /*Planet deletePlanet = new Planet();
        createPlanet.setId(1);
        createPlanet.setName("venus");
        createPlanet.setOwnerId(1);
        PlanetDao planetDao = new PlanetDao();
        Planet expectedPlanet = new Planet();
        expectedPlanet.setName("venus");
        expectedPlanet.setId(1);
        expectedPlanet.setOwnerId(createPlanet.getOwnerId());
        Planet actualPlanet = planetDao.createPlanet(createPlanet,createPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet,expectedPlanet);

         */
    }
    @Test
    public void getPlanetByName()
    {
        Planet getPlanet = new Planet();
        getPlanet.setId(1);
        getPlanet.setName("venus");
        getPlanet.setOwnerId(1);
        PlanetDao planetDao = new PlanetDao();
        planetDao.createPlanet(getPlanet,getPlanet.getOwnerId());
        Planet expectedPlanet = planetDao.getPlanetByName(getPlanet.getName(),getPlanet.getOwnerId());

        Planet actualPlanet = planetDao.getPlanetByName(getPlanet.getName(), getPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet, expectedPlanet);


    }

    @Test
    public void getByPlanetId()
    {
        Planet getPlanet = new Planet();
        getPlanet.setId(1);
        getPlanet.setName("venus");
        getPlanet.setOwnerId(1);
        PlanetDao planetDao = new PlanetDao();
        planetDao.createPlanet(getPlanet,getPlanet.getOwnerId());
        Planet expectedPlaent = planetDao.getPlanetById(getPlanet.getId(),getPlanet.getOwnerId());

        Planet actualPlanet = planetDao.getPlanetById(getPlanet.getId(), getPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet, expectedPlaent);

    }

    @Test
    public void  testGetAllPlanets() {

        //given info to test
        Planet planet = new Planet();
        planet.setId(1);
        planet.setName("Earth");
        planet.setOwnerId(1);

        Planet planet2 = new Planet();
        planet2.setId(2);
        planet2.setName("Mars");
        planet2.setOwnerId(1);

        PlanetDao planetDao = new PlanetDao();
        //create them
        planetDao.createPlanet(planet, planet.getOwnerId());
        planetDao.createPlanet(planet2,planet2.getOwnerId());

        List<Planet> planetList1 = planetDao.getAllPlanets(planet.getOwnerId());
        //List<Planet> planetList2 = planetDao.getAllPlanets(planet2.getOwnerId());


        //check to see that its not null and that list isnt empty
        Assertions.assertNotNull(planetList1);
        Assertions.assertFalse(planetList1.isEmpty());
        Assertions.assertEquals(10,planetList1.size());


    }


}
