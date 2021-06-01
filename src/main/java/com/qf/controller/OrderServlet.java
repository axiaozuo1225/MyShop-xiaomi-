package com.qf.controller;

import com.google.gson.Gson;
import com.qf.bean.*;
import com.qf.service.OrderService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.service.impl.CartServiceImpl;
import com.qf.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String preView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if(loginUser == null){
            return "forward:/login.jsp";
        }

        List<Cart> cartList = new CartServiceImpl().showCartsByUid(loginUser.getUid());
        List<Address> addList = new AddressServiceImpl().showAddressByUid(loginUser.getUid());

        if(cartList != null && addList != null){
            request.setAttribute("cartList",cartList);
            request.setAttribute("addList",addList);
            return "forward:/order.jsp";
        }

        request.setAttribute("msg", "您还没有加入任何商品哦");
        return "forward:/message.jsp";
    }


    /**
     * 显示订单列表页面
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int uid = Integer.parseInt(req.getParameter("uid"));

        List<Orders> ordersList = orderService.showOrders(uid);

        req.setAttribute("orderList",ordersList);
        return "forward:/orderList.jsp";
    }

    /**
     * 添加订单
     * 1.提交订单数据到orders
     * 2.提交订单详情到item
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int uid = Integer.parseInt(req.getParameter("uid"));
        int aid = Integer.parseInt(req.getParameter("aid"));
        BigDecimal money = new BigDecimal(req.getParameter("sum"));

        orderService.addOrder(uid,aid,money);
        return "redirect:/order?method=show&uid=" + uid;

    }


    /**
     * 订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String oid = req.getParameter("oid");

        Orders orders = orderService.detail(oid);
//        System.out.println(orders.getList());

        req.setAttribute("od",orders);
        return "forward:/orderDetail.jsp";

    }

    /**
     * 微信支付结果回调
     * @param req
     * @param resp
     * @return
     */
    public String payResult(HttpServletRequest req, HttpServletResponse resp) {
        //获取回调的oid来找到订单来设置订单状态,获取结果json字符串来判断微信支付是否成功↓
        //成功调用业务层根据oid修改订单状态为2,并后台跳转到我的订单即订单列表,失败就消息转发到消息提示页面↓
        String oid = req.getParameter("oid");

        String result = req.getParameter("result");
        Gson gson = new Gson();
        WeiXin weiXin = gson.fromJson(result, WeiXin.class);

        String result_code = weiXin.getResult().getResult_code();

        if (result_code!=null && result_code.equals("SUCCESS")){
            int count = orderService.updateStateByOid(oid);
            System.out.println("微信支付成功:"+count);

            User user = (User) req.getSession().getAttribute("loginUser");
            int uid = user.getUid();
            return "forward:/order?method=show&uid="+uid;
        }else {
            req.setAttribute("msg", "订单支付失败");
            return "forward:/message.jsp";
        }
    }
}
