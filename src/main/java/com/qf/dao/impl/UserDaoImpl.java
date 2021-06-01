package com.qf.dao.impl;

import com.qf.bean.User;
import com.qf.dao.UserDao;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public User checkUserByName(String username) {
        String sql = "select * from user where username = ?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class), username);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public int insertUser(User registerUser) {
        String sql = "insert into user values (?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            result = queryRunner.update(
                    sql,
                    null,
                    registerUser.getUsername(),
                    registerUser.getPassword(),
                    registerUser.getEmail(),
                    registerUser.getGender(),
                    registerUser.getState(),
                    registerUser.getRole(),
                    registerUser.getCode());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public User checkUserByNameAndPassword(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User checkCode(String code) {

        String sql = "select * from user where code = ?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class), code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void changeState(User user) {
        String sql = "update user set state = 1 where id = ?";
        try {
            queryRunner.update(sql, user.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
