package com.revature.units;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;
import com.revature.service.PlanetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class PlanetUnitTests {



    @Mock
    PlanetDao planetDao;
    @InjectMocks
    PlanetService planetService;

    @Test
    public void createPlanet(){
        Planet planet=new Planet();
        planet.setName("venus");
        planet.setId(1);
        planet.setOwnerId(1);
        Planet newPlanet = new Planet();
        newPlanet.setId(1);
        newPlanet.setName("venus");
        newPlanet.setOwnerId(1);

        Planet actualPlanet= planetService.createPlanet(newPlanet.getOwnerId(),newPlanet);
        Mockito.verify(this.planetDao,times(1)).createPlanet(planet,planet.getOwnerId());
        Assertions.assertEquals(planet,actualPlanet);

    }
}
