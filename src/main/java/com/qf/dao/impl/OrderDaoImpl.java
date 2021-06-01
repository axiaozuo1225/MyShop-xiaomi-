package com.qf.dao.impl;

import com.qf.bean.Item;
import com.qf.bean.Orders;
import com.qf.bean.Address;
import com.qf.dao.AddressDao;
import com.qf.dao.OrderDao;
import com.qf.service.impl.ItemServiceImpl;
import com.qf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OrderDaoImpl implements OrderDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 查看用户订单
     *
     * @param uid
     * @return
     */
    @Override
    public List<Orders> showOrdersByUid(int uid) {

        String sql = "select * from orders o, address a where o.aid = a.aid and o.uid = ?";

        ArrayList<Orders> ordersList = new ArrayList<>();
        try {
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);

            for (Map<String, Object> map : list) {
                try {
                    Orders orders = new Orders();
                    Address address = new Address();
                    BeanUtils.populate(orders, map);
                    BeanUtils.populate(address, map);

                    orders.setAddress(address);
                    ordersList.add(orders);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    /**
     * 添加订单
     *
     * @param orders
     */
    @Override
    public void addOrder(Orders orders) {

        String sql = "insert into orders values (?,?,?,?,?,?)";
        try {
            queryRunner.update(sql, orders.getOid(), orders.getUid(), orders.getAid(), orders.getMoney(), orders.getState(), orders.getTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看单条订单
     */
    public Orders showOrderByOid(String oid) {

        String sql = "select * from orders where oid = ?";

        Orders orders = null;
        try {
            orders = queryRunner.query(sql, new BeanHandler<>(Orders.class), oid);

            Address address = new AddressDaoImpl().showAddressByAid(orders.getAid());
            List<Item> items = new ItemServiceImpl().showItemsByOid(orders.getOid());
//            System.out.println(items);

            orders.setAddress(address);
            orders.setList(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * 更新订单支付状态
     *
     * @param oid
     * @return
     */
    @Override
    public int updateStateByOid(String oid) {

        String sql = "update orders set state = 2 where oid = ?";

        int result = 0;
        try {
            result = queryRunner.update(sql, oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
