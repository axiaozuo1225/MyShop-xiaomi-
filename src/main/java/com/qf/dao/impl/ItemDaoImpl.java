package com.qf.dao.impl;

import com.qf.bean.Item;
import com.qf.bean.Product;
import com.qf.dao.ItemDao;
import com.qf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 插入详情item
     *
     * @param item
     */
    @Override
    public void addItem(Item item) {

        String sql = "insert into item values (?,?,?,?,?)";
        try {
            queryRunner.update(sql, null, item.getPid(), item.getOid(), item.getNum(), item.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过订单编号查询该订单中的商品详情item
     * @param oid
     * @return
     */
    @Override
    public List<Item> showItemsByOid(String oid) {

        String sql = "select * from item i, product p where i.pid = p.pid and oid = ?";

        List<Item> items = new ArrayList<>();
        try {
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), oid);
            for (Map<String, Object> map : list) {
                Item item = new Item();
                //添加product
                Product product = new Product();
                try {
                    BeanUtils.populate(item, map);
                    BeanUtils.populate(product, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                item.setProduct(product);

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}
