package com.qf.dao;

import com.qf.bean.Item;

import java.util.List;

public interface ItemDao {
    void addItem(Item item);

    List<Item> showItemsByOid(String oid);
}
