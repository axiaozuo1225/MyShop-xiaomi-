package com.qf.filter;

import com.qf.bean.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login.jsp")
public class LoginFilter implements Filter {

    private UserService userService = new UserServiceImpl();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //用户访问login之前,确认自动登录,判断有无cookie
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("autoLoginUser".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    String[] strings = value.split(":");
                    String username = strings[0];
                    String password = strings[1];

                    //验证cookie中的用户名和密码
                    User user = userService.login(username, password);
                    if (user != null) {
                        //浏览器关闭,旧session无法找到,再次储存session
                        request.getSession().setAttribute("loginUser", user);
                        //转发到首页
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    break;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
