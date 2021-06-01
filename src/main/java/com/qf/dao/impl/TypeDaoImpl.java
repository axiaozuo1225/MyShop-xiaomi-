package com.qf.dao.impl;

import com.qf.bean.Type;
import com.qf.dao.TypeDao;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    /**
     * 分页查询商品列表
     * @return
     */
    @Override
    public List<Type> showTypeList() {

        List<Type> list = null;

        String sql = "select * from type";
        try {
            list = queryRunner.query(sql, new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 显示商品类别
     * @return
     * @throws Exception
     */
    @Override
    public List<Type> showGoodsType() throws Exception {

        String sql = "select * from type";
        return queryRunner.query(sql, new BeanListHandler<>(Type.class));
    }
}
