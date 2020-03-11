package com.clouds.serviceTest;

import com.clouds.domain.User;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.UserServiceImpl;
import org.junit.Test;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/6 22:13
 * @Version V1.0
 */
public class addUserTest {
    @Test
    public void addUserTest(){
        User user = new User();
        user.setUsername("user5");
        user.setPassword("123456");
        user.setEmail("123@qq.com");
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        System.out.println("成功");
    }
}
