package com.clouds.serviceTest;

import com.clouds.domain.User;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.UserServiceImpl;
import org.junit.Test;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/6 16:11
 * @Version V1.0
 */
public class findUserTest {
    @Test
    public void findUser(){
        User user = new User();
        user.setUsername("user1");
        user.setPassword("123456");
        UserService userService = new UserServiceImpl();
        User user1 = userService.findUser(user);
        System.out.println(user1);
    }
}
