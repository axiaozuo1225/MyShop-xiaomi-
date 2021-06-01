package com.qf.dao.impl;

import com.qf.bean.Address;
import com.qf.dao.AddressDao;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 查询用户所有地址
     *
     * @param uid
     * @return
     */
    public List<Address> showAddressByUid(int uid) {

        String sql = "select * from address where uid = ?";

        List<Address> addressList = null;
        try {
            addressList = queryRunner.query(sql, new BeanListHandler<>(Address.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    /**
     * 添加地址
     *
     * @param address
     */
    @Override
    public void addAddress(Address address) {

        String sql = "insert into address values (?,?,?,?,?,?)";
        try {
            queryRunner.update(sql, null, address.getUid(), address.getDetail(), address.getName(), address.getPhone(), address.getLevel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除地址
     *
     * @param aid
     */
    @Override
    public void delete(int aid) {

        String sql = "delete from address where aid = ?";
        try {
            queryRunner.update(sql, aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置默认地址
     * 设置level等级排序也可以实现默认地址显示
     * select * from address where uid = ? order by level desc
     *
     * @param aid
     */
    @Override
    public void setDefault(int uid, int aid) {

        String sql1 = "update address set level = 0 where uid = ?";
        String sql2 = "update address set level = 1 where aid = ?";
        try {
            queryRunner.update(sql1, uid);
            queryRunner.update(sql2, aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public Address showAddressByAid(int aid) {

        String sql = "select * from address where aid = ?";

        Address address = null;
        try {
            address = queryRunner.query(sql, new BeanHandler<>(Address.class), aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void updateAddress(Address address) {

        String sql = "update address set detail = ?, name = ?, phone = ? where aid = ?";
        try {
            queryRunner.update(sql, address.getDetail(), address.getName(), address.getPhone(), address.getAid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
