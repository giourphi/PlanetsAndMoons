package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Planet;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.PlanetDao;


public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets(int ownerID) {
		// TODO Auto-generated method stub

      return dao.getAllPlanets(ownerID);//returns all the planets in planetarium

    }

	public Planet getPlanetByName(int ownerId, String planetName) {
		// TODO Auto-generated method stub

		return dao.getPlanetByName(planetName,ownerId);//gets planet by the planet name
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		// TODO Auto-generated method stub
		//pass in planetid and ownerid
		return dao.getPlanetById(planetId,ownerId);
	}

	public Planet createPlanet(int ownerId, Planet planet) {
		// TODO Auto-generated method stub
	//check to see if planet name is <=30 characters
		if (planet.getName().length()<=30) {
			//check to see if planetname is unique
			if(dao.getPlanetByName(planet.getName(), planet.getOwnerId())==null) {
				//create the planet and return it
				Planet validPlanetData = new Planet();
				validPlanetData.setName(planet.getName());
				validPlanetData.setOwnerId(planet.getOwnerId());
				// make sure to return the new user's data
				return dao.createPlanet(validPlanetData,ownerId);
			}
		}
//otherwise null, some requriement wasnt fulfilled
		return null;
	}

	public boolean deletePlanetById(int ownerId, int planetId) {
		// TODO Auto-generated method stub

		//if dao.deleteplanetbyid returns true then the planet should be deleted
		if(dao.deletePlanetById(planetId,ownerId))
		{
			return  true;
		}
		//return false if nothing was deleted
		return false;
	}
}
