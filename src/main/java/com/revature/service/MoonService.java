package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.repository.MoonDao;
import com.revature.utilities.ConnectionUtil;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons(int myPlanetid) {
		// TODO implement
		//returns a list of all moons
		return dao.getAllMoons(myPlanetid);

	}

	public Moon getMoonByName(int myPlanetId, String moonName) {
		// TODO implement
		//returns moon by name
		return dao.getMoonByName(moonName,myPlanetId);
	}

	public Moon getMoonById(int myPlanetId, int moonId) {
		// TODO implement
		return  dao.getMoonById(moonId,myPlanetId); //gets the moon by the moon id and planet id
	}

	public Moon createMoon(Moon m) {
		// TODO implement
		//checks to make sure moonname length doesnt exceed 30 characters
		if (m.getName().length()<=30) {

			if(m.getName().isBlank())
			{
				return null;
			}
			else {
				//check to see if moonName is unique

				Moon validMoonData = new Moon();
				validMoonData.setName(m.getName());
				validMoonData.setMyPlanetId(m.getMyPlanetId());
				// make sure to return the new user's data
				return dao.createMoon(validMoonData);
			}


		}
		//returns null if nothing met the requirements for creating a moon
		return null;
	}

	public boolean deleteMoonById(int moonId) {
		//delets moon by its moon id
		if(dao.deleteMoonById(moonId))
		{
			return  true;
		}
		return false;//return false if nothing was deleted

	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		// TODO Auto-generated method stub
		//returns list of moonsfromplanets
		return dao.getMoonsFromPlanet(myPlanetId);
	}
}
