package com.qf.dao.impl;

import com.qf.bean.Cart;
import com.qf.bean.Product;
import com.qf.dao.CartDao;
import com.qf.service.ProductService;
import com.qf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CartDaoImpl implements CartDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 查询单条购物车记录
     *
     * @param pid
     * @param uid
     * @return
     */
    @Override
    public Cart findCartByPidAndUid(int pid, int uid) {

        String sql = "select * from cart where pid = ? and uid = ?";

        Cart cart = null;
        try {
            cart = queryRunner.query(sql, new BeanHandler<>(Cart.class), pid, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //同时查询出cart信息和对应商品的信息
        //不要模棱两可字段
        /*String sql = "select * from cart c, product p where c.pid = p.pid and c.pid = ? and c.uid = ?";
        try {
            Map<String, Object> map = queryRunner.query(sql, new MapHandler(), pid, uid);

            Cart cart = null;
            if (map != null) {
                cart = new Cart();
                Product product = new Product();

                try {
                    BeanUtils.populate(cart, map);
                    BeanUtils.populate(product, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                cart.setProduct(product);
            }
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        return cart;
    }

    /**
     * 更新购物车
     *
     * @param cart
     * @return
     */
    @Override
    public int updateCart(Cart cart) {

        String sql = "update cart set num = ? , money = ? where cid = ?";

        int result = 0;
        try {
            result = queryRunner.update(sql, cart.getNum(), cart.getMoney(), cart.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 新建购物车
     *
     * @param cart
     * @return
     */
    @Override
    public int insertCart(Cart cart) {

        String sql = "insert into cart values (?,?,?,?,?)";

        int result = 0;
        try {
            result = queryRunner.update(sql, null, cart.getPid(), cart.getUid(), cart.getNum(), cart.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询购物车列表
     *
     * @param uid
     * @return
     */
    @Override
    public List<Cart> findCartByUid(int uid) {

        String sql = "select * from cart c, product p where c.pid = p.pid and uid = ?";

        List<Cart> carts = new ArrayList<>();
        try {
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);
            for (Map<String, Object> map : list) {
                Cart cart = new Cart();
                Product product = new Product();
                try {
                    BeanUtils.populate(cart, map);
                    BeanUtils.populate(product, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cart.setProduct(product);

                carts.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carts;
    }

    @Override
    public void deleteCart(String cid) {

        String sql = "delete from cart where cid = ?";
        try {
            queryRunner.update(sql, cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String cid, String money, String num) {

        String sql = "update cart set num = ? , money = ? where cid = ?";
        try {
            queryRunner.update(sql, num, money, cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearCart(String uid) {

        String sql = "delete from cart where uid = ?";
        try {
            queryRunner.update(sql, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
