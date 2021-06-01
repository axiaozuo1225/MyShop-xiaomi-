package com.qf.service;

import com.qf.bean.User;

public interface UserService {

    /**
     * 检查用户名可用
     * @param username
     * @return
     */
    User checkUser(String username);

    /**
     * 注册用户
     * @param registerUser
     * @return
     */
    int register(User registerUser);

    User login(String username, String password);

    User checkCode(String code);

    void changeState(User user);
}
