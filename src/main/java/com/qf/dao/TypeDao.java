package com.qf.dao;

import com.qf.bean.Type;

import java.util.List;

public interface TypeDao {
    /**
     * 查询商品类别表
     * @return
     */
    List<Type> showTypeList();

    List<Type> showGoodsType() throws Exception;
}
