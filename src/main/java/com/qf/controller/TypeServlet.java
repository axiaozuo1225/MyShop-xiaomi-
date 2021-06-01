package com.qf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.bean.Type;
import com.qf.service.TypeService;

import com.qf.service.impl.TypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/type")
public class TypeServlet extends BaseServlet {

    private TypeService  typeService = new TypeServiceImpl();

    /**
     * 查询商品类别
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected String typeListToJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Type> list = typeService.showTypeList();

        //设置json数据类型返回
        response.setContentType("application/json;charset=utf-8");

        //获得json字符串,返回
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        return json;

    }

    /**
     * 展示商品类别
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String showGoodsType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Type> goodsTypeList = null;
        try {
            goodsTypeList = typeService.showGoodsType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (goodsTypeList != null) {
            req.setAttribute("goodsTypeList",goodsTypeList);
            return "forward:/admin/showGoodsType.jsp";
        }
        return null;
    }

}
