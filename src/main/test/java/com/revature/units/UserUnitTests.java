package com.revature.units;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserUnitTests {
    @Mock
    UserDao userDao;
    @InjectMocks
    UserService userService;

    @Test
    public void createAccount(){
        UsernamePasswordAuthentication user=new UsernamePasswordAuthentication();
        user.setUsername("Jasdhir123");
        user.setPassword("pass");
        User createdUser=new User();
        createdUser.setId(1);
        createdUser.setUsername("Jasdhir123");
        createdUser.setPassword("pass");
        when(userDao.createUser(user)).thenReturn(createdUser);
        User actualUser=userService.register(createdUser);
        Mockito.verify(this.userDao,times(1)).createUser(user);
        Assertions.assertEquals(createdUser,actualUser);

    }


}
