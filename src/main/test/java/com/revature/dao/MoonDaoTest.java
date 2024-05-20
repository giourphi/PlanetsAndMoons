package com.revature.dao;

import com.revature.models.Moon;

import com.revature.models.Planet;
import com.revature.repository.MoonDao;
import com.revature.repository.PlanetDao;
import com.revature.utilities.ConnectionUtil;
import io.javalin.Javalin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class MoonDaoTest {


    public Connection connection;
    public Javalin app;
    @BeforeEach
    public void setUp() throws SQLException {
        connection = ConnectionUtil.createConnection();
  /*  app=Javalin.create();
        RequestMapper.setUpEndPoints(app);*/
    }

    @Test
    public void  createMoonTest(){
        Moon createMoon = new Moon();
        createMoon.setId(4);
        createMoon.setName("venusMoon");
        createMoon.setMyPlanetId(createMoon.getMyPlanetId());
        MoonDao moonDAO = new MoonDao();
        Moon expectedMoon = new Moon();
        expectedMoon.setName("venusMoon");
        expectedMoon.setId(4);
        expectedMoon.setMyPlanetId(createMoon.getMyPlanetId());
        Moon actualMoon = moonDAO.createMoon(createMoon);
        Assertions.assertEquals(actualMoon,expectedMoon);
    }

    @Test
    public void getMoonByMoonName()
    {
        Moon getMoon = new Moon();
        getMoon.setId(1);
        getMoon.setName("venusMoon");
        getMoon.setMyPlanetId(1);
        MoonDao moonDao = new MoonDao();
        moonDao.createMoon(getMoon);
        Moon expectedMoon = moonDao.getMoonByName(getMoon.getName(),getMoon.getMyPlanetId());

        Moon actualPlanet = moonDao.getMoonByName(getMoon.getName(), getMoon.getMyPlanetId());
        Assertions.assertEquals(actualPlanet,expectedMoon);


    }


    @Test
    public void getByMoonID()
    {
        Moon getMoon = new Moon();
        getMoon.setId(1);
        getMoon.setName("venusMoon");
        getMoon.setMyPlanetId(1);
        MoonDao moonDao = new MoonDao();
        moonDao.createMoon(getMoon);
        Moon expectedMoon = moonDao.getMoonById(getMoon.getId(),getMoon.getMyPlanetId());

        Moon actualMoon = moonDao.getMoonById(getMoon.getId(), getMoon.getMyPlanetId());
        Assertions.assertEquals(actualMoon, expectedMoon);

    }


}
