package com.qf.dao.impl;

import com.qf.bean.Product;
import com.qf.dao.ProductDao;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public int getTotalCount(int tid) {

        String sql = "select count(*) from product where tid = ?";

        int totalCount = 0;
        try {
            totalCount = ((Long) queryRunner.query(sql, new ScalarHandler(), tid)).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCount;
    }

    @Override
    public List<Product> showProductByPage(int tid, int startPage, int pageNum) {

        String sql = "select * from product where tid = ? limit ?, ?";

        List<Product> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(Product.class), tid, startPage, pageNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 根据pid查询商品
     *
     * @param pid
     * @return
     */
    @Override
    public Product getProductByPid(int pid) {

        String sql = "select * from product where pid =  ?";

        Product product = null;
        try {
            product = queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    /**
     * 加入商品
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) throws Exception {

        Object[] args = {null, product.getTid(), product.getName(), product.getPubdate(), product.getPicture(), product.getPrice(), product.getStar(), product.getInfo()};
        String sql = "insert into product values (?,?,?,?,?,?,?,?)";
        queryRunner.update(sql, args);
    }
}
