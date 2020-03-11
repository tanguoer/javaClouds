package com.clouds.service;

import com.clouds.domain.User;

/**
 * @Description TODO
 * @ClassName UserService
 * @Author lly
 * @Date 2020/3/6 13:30
 * @Version V1.0
 */
public interface UserService {
    User findUser(User user);

    User findUserByUserName(String username);

    void addUser(User user);

    void updateUserById(User user);

    //修改用户使用空间
    void updateUserUseStorageByUser(User user,long length);


    User findUserById(Integer id);
}
