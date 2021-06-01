package com.qf.service.impl;

import com.qf.bean.User;
import com.qf.dao.UserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User checkUser(String username) {
        User user = userDao.checkUserByName(username);
        return user;
    }

    @Override
    public int register(User registerUser) {
        return userDao.insertUser(registerUser);
    }

    @Override
    public User login(String username, String password) {

        return userDao.checkUserByNameAndPassword(username, password);

    }

    @Override
    public User checkCode(String code) {
        return userDao.checkCode(code);
    }

    @Override
    public void changeState(User user) {
        userDao.changeState(user);
    }
}
