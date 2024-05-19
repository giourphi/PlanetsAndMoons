package com.revature.dao;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.RequestMapper;
import io.javalin.Javalin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
public class UserDaoTest {


    public Connection connection;
    public Javalin app;
    @BeforeEach
    public void setUp() throws SQLException {
        connection = ConnectionUtil.createConnection();
  /*  app=Javalin.create();
        RequestMapper.setUpEndPoints(app);*/
    }

    @Test
    public void registerUserPositive(){
        UsernamePasswordAuthentication user=new UsernamePasswordAuthentication();
        user.setUsername("Alex");
        user.setPassword("pass");
        UserDao userDao=new UserDao();
        User expectedUser=new User();
        User actualUser= userDao.createUser(user);
        Assertions.assertEquals(actualUser,expectedUser);
    }

    @Test
    public void registerUserNegative(){
        UsernamePasswordAuthentication user=new UsernamePasswordAuthentication();
        user.setUsername("Alex");
        user.setPassword("pass");
        UserDao userDao=new UserDao();
        User expectedUser=new User();
        User actualUser= userDao.createUser(user);
        Assertions.assertEquals(actualUser,expectedUser);
    }

    @Test
    public void loginUserPositive()
    {
       /* UsernamePasswordAuthentication user=new UsernamePasswordAuthentication();
        user.setUsername("Alex");
        user.setPassword("pass");
        UserDao userDao=new UserDao();
        User expectedUser=new User();
        User actualUser= userDao.createUser(user);
        Assertions.assertEquals(actualUser,expectedUser);
        */

    }


}
