package com.qf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.bean.Type;
import com.qf.bean.User;
import com.qf.service.AdminService;
import com.qf.service.impl.AdminServiceImpl;
import com.qf.utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends BaseServlet {

    private AdminService adminService = new AdminServiceImpl();

    protected String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = MD5Utils.md5(password);

        User admin = null;
        try {
            admin = adminService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
            return "forward:/admin/admin.jsp";
        }

        request.setAttribute("msg", "用户名或密码错误");
        return "forward:/message.jap";

    }


    /**
     * 展示所有用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> userList = null;
        try {
            userList = adminService.showUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userList != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(userList);
            return json;
        }

        return null;
    }

    /**
     * 删除用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int uid = Integer.parseInt(req.getParameter("uid"));

        int result = 0;
        try {
            result = adminService.deleteUser(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > 0) {
            return "redirect:/admin?method=list";
        }

        return null;
    }

    /**
     * 查询用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String searchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String gender = req.getParameter("gender");

        List<User> searchUsers = null;
        try {
            searchUsers = adminService.searchUser(username, gender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (searchUsers != null) {
            if (searchUsers.size() == 0) {
                return "0";
            } else {
                return new ObjectMapper().writeValueAsString(searchUsers);
            }
        }
        return null;
    }

    /**
     * 管理员注销
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("admin");
        return "redirect:/admin/login.jsp";
    }



}
