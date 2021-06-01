package com.qf.controller;

import com.qf.bean.Address;
import com.qf.bean.User;
import com.qf.service.AddressService;
import com.qf.service.impl.AddressServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/address")
public class AddressServlet extends BaseServlet {

    private AddressService addressService = new AddressServiceImpl();

    /**
     * 查询收获地址
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            return "forward:/login.jsp";
        }

        List<Address> addList = addressService.showAddressByUid(loginUser.getUid());

        if (addList != null) {
            request.setAttribute("addList", addList);
            return "forward:/self_info.jsp";
        }

        return "forward:/message.jsp";

    }

    /**
     * 添加收货地址
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = (User) req.getSession().getAttribute("loginUser");

        Address address = new Address();
        address.setUid(loginUser.getUid());
        address.setLevel(0);

        Map<String, String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(address, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addressService.addAddress(address);
        return "redirect:/address?method=show";
    }

    /**
     * 删除地址
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int aid = Integer.parseInt(req.getParameter("aid"));

        addressService.delete(aid);
        return "redirect:/address?method=show";

    }

    /**
     * 修改地址信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = (User) req.getSession().getAttribute("loginUser");

        Map<String, String[]> map = req.getParameterMap();
        Address address = new Address();
        address.setUid(loginUser.getUid());
        try {
            BeanUtils.populate(address, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addressService.updateAddress(address);
        return "redirect:/address?method=show";

    }

    /**
     * 设置默认地址
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String setDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = (User) req.getSession().getAttribute("loginUser");
        int aid = Integer.parseInt(req.getParameter("aid"));

        addressService.setDefault(loginUser.getUid(),aid);
        return "redirect:/address?method=show";
    }
}
