package com.qf.dao;

import com.qf.bean.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 计算某类商品总数量
     * @param tid
     * @return
     */
    int getTotalCount(int tid);

    /**
     * 分页查询某类商品
     * @param tid
     * @param startPage
     * @param pageNum
     * @return
     */
    List<Product> showProductByPage(int tid, int startPage, int pageNum);

    /**
     * 查看商品详情
     * @param pid
     * @return
     */
    Product getProductByPid(int pid);

    void addProduct(Product product) throws Exception;
}
