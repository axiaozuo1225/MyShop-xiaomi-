package com.qf.service.impl;

import com.qf.bean.Cart;
import com.qf.bean.Product;
import com.qf.dao.CartDao;
import com.qf.dao.impl.CartDaoImpl;
import com.qf.service.CartService;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceImpl implements CartService {

    private CartDao cartDao = new CartDaoImpl();

    /**
     * 加入新的购物车
     *
     * @param pid
     * @param uid
     * @return
     */
    @Override
    public int createCart(int pid, int uid) {

        //判断购物车中是否已经存在同一用户的同一商品
        Cart cart = cartDao.findCartByPidAndUid(pid, uid);

        //查询商品的信息赋值给cart.product可以在数据库中一次查出来-->cartDao
        // 根据pid查询商品的信息
        Product product = new ProductServiceImpl().getProductByPid(pid);

        int result = 0;
        if (null != cart) {
            //购物车中已经存在相同的商品
            cart.setNum(cart.getNum() + 1);
            cart.setProduct(product);
            result = cartDao.updateCart(cart);
        } else {
            //购物车不存在该商品,创建购物车
            Cart newCart = new Cart();
            newCart.setPid(pid);
            newCart.setUid(uid);
            newCart.setNum(1);
            newCart.setProduct(product);
            result = cartDao.insertCart(newCart);
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
    public List<Cart> showCartsByUid(int uid) {
        return cartDao.findCartByUid(uid);
    }

    /**
     * 删除数量0购物车
     *
     * @param cid
     */
    @Override
    public void deleteCart(String cid) {
        cartDao.deleteCart(cid);
    }

    /**
     * 购物车商品数量加减
     *
     * @param cid
     * @param price
     * @param num
     */
    @Override
    public void update(String cid, String price, String num) {
        //计算总金额
        BigDecimal bigPrice = new BigDecimal(price);
        BigDecimal bigMoney = bigPrice.multiply(new BigDecimal(num));
        String money = bigMoney.toString();

        cartDao.update(cid, money, num);
    }

    @Override
    public void clearCart(String uid) {
        cartDao.clearCart(uid);
    }
}
