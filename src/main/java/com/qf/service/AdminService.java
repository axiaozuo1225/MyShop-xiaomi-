package com.qf.service;

import com.qf.bean.Type;
import com.qf.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {

    User login(String username, String password) throws Exception;

    List<User> showUserList() throws Exception;

    int deleteUser(int uid) throws Exception;

    List<User> searchUser(String username, String gender) throws Exception;

}
