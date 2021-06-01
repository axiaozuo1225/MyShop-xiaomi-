package com.qf.service;

import com.qf.bean.Type;

import java.util.List;

public interface TypeService {
    /**
     * 查询商品类别
     * @return
     */
    List<Type> showTypeList();

    List<Type> showGoodsType() throws Exception;
}
