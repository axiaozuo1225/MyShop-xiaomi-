package com.qf.service;

import com.qf.bean.Cart;

import java.util.List;

public interface CartService {

    int createCart(int pid, int uid);

    List<Cart> showCartsByUid(int uid);

    void deleteCart(String cid);

    void update(String cid, String money, String num);

    void clearCart(String uid);
}
