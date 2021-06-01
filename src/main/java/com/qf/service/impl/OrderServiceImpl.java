package com.qf.service.impl;

import com.qf.bean.Address;
import com.qf.bean.Cart;
import com.qf.bean.Item;
import com.qf.bean.Orders;
import com.qf.dao.AddressDao;
import com.qf.dao.CartDao;
import com.qf.dao.ItemDao;
import com.qf.dao.OrderDao;
import com.qf.dao.impl.AddressDaoImpl;
import com.qf.dao.impl.CartDaoImpl;
import com.qf.dao.impl.ItemDaoImpl;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.service.OrderService;
import com.qf.utils.RandomUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    private ItemDao itemDao = new ItemDaoImpl();

    /**
     * 查看订单
     * @param uid
     * @return
     */
    @Override
    public List<Orders> showOrders(int uid) {
        return orderDao.showOrdersByUid(uid);
    }

    /**
     * 插入订单
     * @param uid
     * @param aid
     * @param money
     */
    @Override
    public void addOrder(int uid, int aid, BigDecimal money) {

        //创建订单对象
        Orders orders = new Orders();
        orders.setOid(RandomUtils.createOrderId());
        orders.setUid(uid);
        orders.setAid(aid);
        orders.setMoney(money);
        orders.setState(1);
        orders.setTime(new Date());

        //要在插入item之前就插入,不然插入item时,没有对应的oid,会报错
        orderDao.addOrder(orders);

        //创建item集合
        ArrayList<Item> items = new ArrayList<>();
        //item和cart基本相同
        List<Cart> cartList = cartDao.findCartByUid(uid);
        for (Cart cart : cartList) {
            Item item = new Item();
            item.setPid(cart.getPid());
            item.setOid(orders.getOid());
            item.setNum(cart.getNum());
            item.setMoney(cart.getMoney());
            itemDao.addItem(item);
        }


    }

    /**
     * 展示订单详情
     * @param oid
     * @return
     */
    @Override
    public Orders detail(String oid) {
        return orderDao.showOrderByOid(oid);
    }

    @Override
    public int updateStateByOid(String oid) {
        return orderDao.updateStateByOid(oid);
    }
}
