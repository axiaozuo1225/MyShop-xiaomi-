package com.qf.service.impl;

import com.qf.bean.Page;
import com.qf.bean.Product;
import com.qf.dao.ProductDao;
import com.qf.dao.impl.ProductDaoImpl;
import com.qf.service.ProductService;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    /**
     * 显示商品列表
     *
     * @param tid
     * @param curPage
     * @param pageNum
     * @return
     */
    @Override
    public Page<Product> showProductByPage(int tid, int curPage, int pageNum) {

        //新建page对象.作为返回值
        Page<Product> page = new Page<>();
        //设置每页记录数
        page.setPageNum(pageNum);

        //设计总记录数
        int totalCount = productDao.getTotalCount(tid);
        page.setTotalCount(totalCount);

        //设置总页数
        page.setTotalPage(totalCount % pageNum == 0 ? totalCount / pageNum : totalCount / pageNum + 1);

        //判断页码范围
        if (curPage >= page.getTotalPage()) {
            curPage = page.getTotalPage();
        }
        if (curPage <= 1) {
            curPage = 1;
        }

        //设置当前页页数
        page.setCurPage(curPage);

        //分页查询商品记录数
        int startPage = (curPage - 1) * pageNum;
        List<Product> list = productDao.showProductByPage(tid, startPage, pageNum);
        page.setList(list);

        return page;
    }

    @Override
    public Product getProductByPid(int pid) {

        return productDao.getProductByPid(pid);
    }

    @Override
    public void addProduct(Product product) throws Exception {
        productDao.addProduct(product);
    }
}
