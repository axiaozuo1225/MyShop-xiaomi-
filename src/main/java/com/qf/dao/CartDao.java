package com.qf.dao;

import com.qf.bean.Cart;

import java.util.List;

public interface CartDao {
    Cart findCartByPidAndUid(int pid, int uid);

    int updateCart(Cart cart);

    int insertCart(Cart cart);

    List<Cart> findCartByUid(int uid);

    void deleteCart(String cid);

    void update(String cid, String money, String num);

    void clearCart(String uid);
}
