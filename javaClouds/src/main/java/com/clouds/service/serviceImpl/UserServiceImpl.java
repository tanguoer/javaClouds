package com.clouds.service.serviceImpl;

import com.clouds.dao.UserDao;
import com.clouds.dao.daoImpl.UserDaoImpl;
import com.clouds.domain.User;
import com.clouds.service.UserService;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/6 15:54
 * @Version V1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User findUser(User user) {
        return userDao.findUser(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
        return;
    }

    @Override
    public void updateUserById(User user) {
        userDao.updateUserById(user);
    }

    //修改用户使用空间
    @Override
    public void updateUserUseStorageByUser(User user,long length) {
        userDao.updateUserUseStorageByUser(user,length);
    }

    //通过ID查询用户
    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }
}
