package com.qf.service;

import com.qf.bean.Page;
import com.qf.bean.Product;

public interface ProductService {
    /**
     * 分页查询某类商品
     * @param tid
     * @param curPage
     * @param pageNum
     * @return
     */
    Page<Product> showProductByPage(int tid, int curPage, int pageNum);

    Product getProductByPid(int pid);

    void addProduct(Product product) throws Exception;
}
