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
        Planet expectedPlaent = new Planet();
        expectedPlaent.getId();
        expectedPlaent.getName();
        expectedPlaent.getOwnerId();
        Planet actualPlanet = planetDao.getPlanetByName(getPlanet.getName(), getPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet, expectedPlaent);


    }

    @Test
    public void getByPlanetId()
    {
        Planet getPlanet = new Planet();
        getPlanet.setId(1);
        getPlanet.setName("venus");
        getPlanet.setOwnerId(1);
        PlanetDao planetDao = new PlanetDao();
        Planet expectedPlaent = new Planet();
        expectedPlaent.setId(expectedPlaent.getId());
        expectedPlaent.setName(expectedPlaent.getName());
        expectedPlaent.setOwnerId(expectedPlaent.getOwnerId());
        Planet actualPlanet = planetDao.getPlanetById(getPlanet.getId(), getPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet, expectedPlaent);


    }

    @Test
    public void  getAllPlanets() {

        List<Planet> planets = new ArrayList<>();

        Planet getPlanet = new Planet();

        getPlanet.setId(1);
        getPlanet.setName("valuePlanet");
        getPlanet.setOwnerId(1);

        planets.add(getPlanet);

        PlanetDao planetDao = new PlanetDao();
        Planet expectedPlaent = new Planet();
        expectedPlaent.getId();
        expectedPlaent.getName();
        expectedPlaent.getOwnerId();
        Planet actualPlanet = planetDao.getPlanetByName(getPlanet.getName(), getPlanet.getOwnerId());
        Assertions.assertEquals(actualPlanet, expectedPlaent);

    }


}
