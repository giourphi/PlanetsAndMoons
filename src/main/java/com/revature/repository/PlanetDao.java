package com.revature.repository;


import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetDao {
    
    public List<Planet> getAllPlanets(int ownerID)  {
		// TODO: implement
		//array list to store the planets
		List<Planet> planets = new ArrayList<>();
	//try a connection to sql server for query
		try(Connection connection = ConnectionUtil.createConnection())
		{
			//string statement to make a query
			String sql = "SELECT* FROM planets WHERE ownerId=?";
			//prepared statment with connection for query

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	//result of query stored in resultset upon query execution
			preparedStatement.setInt(1, ownerID);

			ResultSet rs = preparedStatement.executeQuery();
			//while loop to go through the result set and add planets to the list
			while(rs.next())
			{
				//make a new planet and get all the values from table, id,name, and ownerID
				Planet planet = new Planet();
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
				planets.add(planet);
			}
//catch block just in case of sql exception
		}catch (SQLException e)
		{//message printed out for the exception
			System.out.println(e.getMessage());
		}//return all the planets
		return planets;
	}

	public Planet getPlanetByName(String planetName,int ownerID) {
		// TODO: implement

		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from planets where name = ? AND ownerId=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, planetName);
			ps.setInt(2, ownerID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Planet foundPlanet = new Planet();
				foundPlanet.setId(rs.getInt("id"));
				foundPlanet.setName(rs.getString("name"));
				foundPlanet.setOwnerId(rs.getInt("ownerId"));
				return foundPlanet;
			}
			return null;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Planet getPlanetById(int planetId, int ownerID) {
		// TODO: implement

//try statement to get the planet by its planetID and only the owner who is logged in
		try(Connection connection = ConnectionUtil.createConnection())
		{
			//sql statement verifys the id and the ownerid to grab
			String sql = "SELECT* FROM planets WHERE id=? AND ownerId=?";
			//preparedstatment for sql connection
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,planetId);//set in for planet id as incremental
			preparedStatement.setInt(2,ownerID);
			ResultSet rs = preparedStatement.executeQuery();//execute query and store in resultset
			if(rs.next())//if theres a next for the result set
			{//store it in a new planet that was found with the id,name and ownerid
				Planet planetsfound = new Planet();
				planetsfound.setId(rs.getInt("id"));
				planetsfound.setName(rs.getString("name"));
				planetsfound.setOwnerId(rs.getInt("ownerId"));
				//return the planet
				return planetsfound;
			}
			return null;//other wise return null
		}catch (SQLException e)//catch block for sql exception
		{//message prtinting out the error message
			System.out.println(e.getMessage());
		}
		//if connection failed then null
		return null;
	}

	public Planet createPlanet(Planet p, int ownerId) {
		// TODO: implement

		//trys a to create a connection to create the planet
		try(Connection connection = ConnectionUtil.createConnection())
		{
			//sql statement to insert new planet
			String sql = "INSERT INTO planets (name,ownerId) VALUES(?,?)";
			//generate random key for the id value making it go from an incremental state like 1, 2,3
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			//prepared statment to get the name,and ownerid for the planet
			preparedStatement.setString(1,p.getName());
			preparedStatement.setInt(2,p.getOwnerId());
			//executing update for prepared statement
			preparedStatement.executeUpdate();

			//getting primarykeyresultset that is being generated
			ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
			//if statement where inside we create a new planet and return that planet, otherwise just return the newly created planet
			if(pkeyResultSet.next())
			{//creat the planet and store the information then return the created planet
				Planet createPlanet = new Planet();
				createPlanet.setId(pkeyResultSet.getInt(1));
				createPlanet.setName(p.getName());
				createPlanet.setOwnerId(ownerId);
				return createPlanet;
			}
			//otherwise return a new planet
			return new Planet();
		}
		catch (SQLException e){//sql catch exception message printed and new planet returned
			System.out.println(e.getMessage());
			return new Planet();
		}


	}

	public boolean deletePlanetById(int planetId,int ownerID) {
		// TODO: implement
		//sql statment to delete from planets database if id and ownerid match
		String sql = "DELETE FROM planets WHERE id=? AND ownerId=?";
		try(Connection connection = ConnectionUtil.createConnection())
		{
			//try a connection and then make the connection with sql statment passed in
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,planetId);
			preparedStatement.setInt(2,ownerID);
			preparedStatement.executeUpdate();//executed the updated code so data is deleted
			return true;//return true if everything worked

		}
		catch (SQLException e){//exception to catch any errors and message printed if any exceptions caught
			System.out.println(e.getMessage());

		}//if everything failed return false
		return false;

	}
}
