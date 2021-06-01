package com.qf.controller;

import com.qf.bean.Cart;
import com.qf.bean.Product;
import com.qf.bean.User;
import com.qf.service.CartService;
import com.qf.service.impl.CartServiceImpl;
import com.qf.service.impl.ProductServiceImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends BaseServlet {

    private CartService cartService = new CartServiceImpl();

    /**
     * 加入购物车
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected String createCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pid和uid确定一条购物车记录
        User user = (User) request.getSession().getAttribute("loginUser");
        if (null == user) {
            return "redirect:/login.jsp";
        }
        int uid = user.getUid();
        int pid = Integer.parseInt(request.getParameter("pid"));

        //添加购物车
        int result = cartService.createCart(pid, uid);

        if (result > 0) {
            //跳转到添加购物车成功

            return "forward:/cartSuccess.jsp";
        }

        request.setAttribute("msg", "加入购物车失败!");
        return "forward:/message.jsp";
    }


    /**
     * 查看购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String showCartsByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = (User) req.getSession().getAttribute("loginUser");

        if(loginUser == null){
            return "forward:/login.jsp";
        }

        List<Cart> carts = cartService.showCartsByUid(loginUser.getUid());

        if (carts != null) {
            req.setAttribute("carts", carts);
            return "forward:/cart.jsp";
        }

        req.setAttribute("msg", "您还没有加入任何商品哦");
        return "forward:/message.jsp";
    }

    /**
     * 数量减为0,删除该购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cid = req.getParameter("cid");

        cartService.deleteCart(cid);

        return "redirect:/cart?method=showCartsByUid";
    }


    /**
     * 更新购物车数据
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cid = req.getParameter("cid");
        String price = req.getParameter("price");
        String num = req.getParameter("num");

        cartService.update(cid, price, num);

        return "redirect:/cart?method=showCartsByUid";

    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uid = req.getParameter("uid");

        cartService.clearCart(uid);

        return "redirect:/cart?method=showCartsByUid";

    }
}
