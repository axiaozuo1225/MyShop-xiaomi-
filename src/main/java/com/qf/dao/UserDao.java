package com.qf.dao;

import com.qf.bean.User;

public interface UserDao {
    /**
     * 检查用户名可用
     * @param username
     * @return
     */
    User checkUserByName(String username);

    /**
     * 插入用户
     * @param registerUser
     * @return
     */
    int insertUser(User registerUser);

    User checkUserByNameAndPassword(String username, String password);

    User checkCode(String code);

    void changeState(User user);
}
