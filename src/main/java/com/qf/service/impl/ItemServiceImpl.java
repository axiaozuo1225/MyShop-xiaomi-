package com.qf.service.impl;

import com.qf.bean.Item;
import com.qf.dao.ItemDao;
import com.qf.dao.impl.ItemDaoImpl;
import com.qf.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = new ItemDaoImpl();

    /**
     * 通过订单编号查询该订单中的商品详情item
     * @param oid
     * @return
     */
    public List<Item> showItemsByOid(String oid) {

        List<Item> items = itemDao.showItemsByOid(oid);
        return items;
    }
}
