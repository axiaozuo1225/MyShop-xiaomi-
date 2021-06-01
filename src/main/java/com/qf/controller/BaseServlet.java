package com.qf.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("method");
        if (null == methodName || "".equals(methodName)) {
            resp.sendRedirect("index.jsp");
        }

        if (null != methodName) {
            Class<? extends BaseServlet> clazz = this.getClass();
            try {

                System.out.println(methodName);

                Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                if (null != method) {
                    String result = (String) method.invoke(this, req, resp);
                    //"redirect:/index.jsp";//2根据子类方法的返回值判断以什么开头就截取返回值中的文件路径,进行重定向或者转发逻辑
                    if (result != null) {
                        if (result.startsWith("redirect:/")) {
                            String path = result.substring(result.indexOf("/") + 1);
                            resp.sendRedirect("/" + path);
                        } else if (result.startsWith("forward:/")) {
                            String path = result.substring(result.indexOf("/") + 1);
                            req.getRequestDispatcher("/" + path).forward(req, resp);
                        } else {
                            resp.getWriter().write(result);
                        }
                    }
                }
            } catch (Exception e) {
                resp.sendRedirect("index.jsp");
                e.printStackTrace();
            }
        }
    }
}
