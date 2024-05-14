package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons(int myPlanetid) {
		// TODO: implement
		List<Moon> moons = new ArrayList<>();
//list of moons to grab, grab all the moons that are in planatarium
		try(Connection connection = ConnectionUtil.createConnection())
		{//sql statment to select all the moons
			String sql = "SELECT* FROM moons ";
			//preparedstatment to connect the sql statement
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setInt(1,myPlanetid);
//resultset that stores executed query of preparedstatment
			ResultSet rs = preparedStatement.executeQuery();
			//while loop to loop through the entire result set
			while(rs.next())
			{//moon to  grab data of moon sql table
				Moon moon = new Moon();
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				moons.add(moon);//add the moon to the list
			}
//catch statement to catch any sql errors
		}catch (SQLException e)
		{//output the message of the error
			System.out.println(e.getMessage());
		}
		//return the list of moons
		return moons;
	}

	public Moon getMoonByName(String moonName,int myPlanetID) {
		// TODO: implement
	//try connection
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons where name = ?";
			//sql statment to get moons by name, search for them
			// pass statment through to prepared statement execute update and store in result set
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
			//ps.setInt(2,myPlanetID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){//check if resultset has next then make new moon and get the data nad return otherwise return null
				Moon foundMoon = new Moon();
				foundMoon.setId(rs.getInt("id"));
				foundMoon.setName(rs.getString("name"));
				foundMoon.setMyPlanetId(rs.getInt("myPlanetId"));
				return foundMoon;
			}
			return null;//catch statment to catch sql errors
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Moon getMoonById(int moonId,int myPlanetID) {
		try(Connection connection = ConnectionUtil.createConnection())
		{	//String sql = "SELECT planets.id, planets.name,planets.ownerId FROM planets INNER JOIN moons ON moons.myPlanetId = planets.id WHERE moons.myPlanetId=? AND planets.id=?";
			String sql = "Select* From moons where moons.id=?";//get moon by moonid
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,moonId);
			//preparedStatement.setInt(2,myPlanetID);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())//has next for resultset to check if there is a moonid that matches and exists
			{
				Moon moonsFound = new Moon();
				moonsFound.setId(rs.getInt("id"));
				moonsFound.setName(rs.getString("name"));
				moonsFound.setMyPlanetId(rs.getInt("myPlanetId"));
				return moonsFound;//return that moon if it exists
			}
			return null;
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Moon createMoon(Moon m) {
		// TODO: implement
//create a moon
		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "INSERT INTO moons (name,myPlanetId) VALUES(?,?)";//insert it into the database upon creation, generate keys for id
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1,m.getName());
			preparedStatement.setInt(2,m.getMyPlanetId());

			preparedStatement.executeUpdate();//execute sql update statement
			ResultSet pkyResultSet = preparedStatement.getGeneratedKeys();//pkyresult set for generated keys
			if(pkyResultSet.next())
			{//if statment checking if anything was created and if so grab the data and return the newly created moon
				Moon createMoon = new Moon();
				createMoon.setId(pkyResultSet.getInt(1));
				createMoon.setName(m.getName());
				createMoon.setMyPlanetId(m.getMyPlanetId());
				return createMoon;
			}
			return new Moon();//otherwise just return a new moon
		}
		catch (SQLException e){//sql catch statement for any errors that might arise
			System.out.println(e.getMessage());
			return new Moon();
		}
	}

	public boolean deleteMoonById(int moonId) {

		String sql = "DELETE FROM moons WHERE id=?";
		try(Connection connection = ConnectionUtil.createConnection())
		{//delete moons by id
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,moonId);
			preparedStatement.executeUpdate();//execute update on whether or not it actually deleted via sql statement
			return true;//returns true if it succeeeded

		}
		catch (SQLException e){
			System.out.println(e.getMessage());//sql exception to catch if it doesnt work

		}
		return false;//return false if nothing got deleted
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		// TODO: implement

		List<Moon> moons = new ArrayList<>();//array list to store moon by planet id
		//gets moon from the planet

		try(Connection connection = ConnectionUtil.createConnection())
		{
			String sql = "SELECT* FROM moons WHERE moons.myPlanetID="+planetId;//select moons that correspond to a certain planet id
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{//loops through result query to see how many moons relate to that planet id and then add them to the list
				Moon moon = new Moon();
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				moons.add(moon);
			}

		}catch (SQLException e)//throws sql exception
		{
			System.out.println(e.getMessage());//message gets printed
		}
		return moons;//returns moon list
	}
}
