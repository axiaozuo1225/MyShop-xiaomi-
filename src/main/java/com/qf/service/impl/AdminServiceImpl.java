package com.qf.service.impl;

import com.qf.bean.Type;
import com.qf.bean.User;
import com.qf.dao.AdminDao;
import com.qf.dao.impl.AdminDaoImpl;
import com.qf.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public User login(String username, String password) throws Exception {

        return adminDao.login(username, password);
    }

    @Override
    public List<User> showUserList() throws Exception {
        return adminDao.showUserList();
    }

    @Override
    public int deleteUser(int uid) throws Exception {
        return adminDao.deleteUser(uid);
    }

    @Override
    public List<User> searchUser(String username, String gender) throws Exception {
        return adminDao.searchUser(username, gender);
    }


}
