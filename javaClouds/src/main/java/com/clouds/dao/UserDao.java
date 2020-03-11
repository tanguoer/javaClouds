package com.clouds.dao;

import com.clouds.domain.User;

/**
 * @Description TODO
 * @ClassName UserDao
 * @Author lly
 * @Date 2020/3/6 12:05
 * @Version V1.0
 */
public interface UserDao {
    User findUser(User user);

    User findUserByUserName(String username);

    void addUser(User user);

    void updateUserById(User user);

    //修改用户使用空间
    void updateUserUseStorageByUser(User user,Long UseStorage);

    User findUserById(Integer id);
}
