package com.qf.service.impl;

import com.qf.bean.Type;
import com.qf.dao.TypeDao;
import com.qf.dao.impl.TypeDaoImpl;
import com.qf.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {

    private TypeDao typeDao = new TypeDaoImpl();

    @Override
    public List<Type> showTypeList() {
        return typeDao.showTypeList();
    }

    @Override
    public List<Type> showGoodsType() throws Exception {
        return typeDao.showGoodsType();
    }
}
