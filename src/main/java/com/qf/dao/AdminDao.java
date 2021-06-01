package com.qf.dao;

import com.qf.bean.Type;
import com.qf.bean.User;

import java.util.List;

public interface AdminDao {

    User login(String username, String password) throws Exception;


    List<User> showUserList() throws Exception;

    int deleteUser(int uid) throws Exception;

    List<User> searchUser(String username, String gender) throws Exception;

}
