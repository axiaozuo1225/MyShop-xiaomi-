package com.qf.dao;

import com.qf.bean.Cart;
import com.qf.bean.Orders;

import java.util.List;

public interface OrderDao {

    List<Orders> showOrdersByUid(int uid);

    void addOrder(Orders orders);

    Orders showOrderByOid(String oid);

    int updateStateByOid(String oid);
}
