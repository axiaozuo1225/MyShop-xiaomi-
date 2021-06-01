package com.qf.dao.impl;

import com.qf.bean.Type;
import com.qf.bean.User;
import com.qf.dao.AdminDao;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public User login(String username, String password) throws Exception {

        String sql = "select * from user where username = ? and password = ? and role = 1";

        User user = queryRunner.query(sql, new BeanHandler<>(User.class), username, password);

        return user;
    }

    /**
     * 查看用户列表
     *
     * @return
     */
    @Override
    public List<User> showUserList() throws Exception {

        String sql = "select * from user";

        List<User> userList = queryRunner.query(sql, new BeanListHandler<>(User.class));

        return userList;
    }

    /**
     * 删除用户
     *
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public int deleteUser(int uid) throws Exception {

        String sql = "delete from user where uid = ?";

        return queryRunner.update(sql, uid);
    }

    /**
     * 查找用户
     * @param username
     * @param gender
     * @return
     * @throws Exception
     */
    @Override
    public List<User> searchUser(String username, String gender) throws Exception {

        String sql = "select * from user where username = ? and gender = ?";

        List<User> userList = queryRunner.query(sql, new BeanListHandler<>(User.class), username, gender);

        return userList;
    }


}
