package com.qf.service;

import com.qf.bean.Cart;
import com.qf.bean.Orders;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Orders> showOrders(int aid);

    void addOrder(int uid, int aid, BigDecimal money);

    Orders detail(String oid);

    int updateStateByOid(String oid);
}
