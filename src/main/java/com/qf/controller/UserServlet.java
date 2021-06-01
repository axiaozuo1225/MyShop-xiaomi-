package com.qf.controller;

import cn.dsna.util.images.ValidateCode;
import com.qf.bean.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Base64Utils;
import com.qf.utils.EmailUtils;
import com.qf.utils.MD5Utils;
import com.qf.utils.RandomUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 检查用户名可用
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        User user = userService.checkUser(username);

        if (user != null) {
            return "1";
        }
        return "0";
    }

    /**
     * 注册
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> parameterMap = request.getParameterMap();
        User registerUser = new User();
        int result = 0;
        try {
            BeanUtils.populate(registerUser, parameterMap);
            //插入表单未赋值的字段
            registerUser.setState(0);//0非激活状态
            registerUser.setRole(0);//0普通会员
            registerUser.setCode(RandomUtils.createActive());//工具类创建激活码

            registerUser.setPassword(MD5Utils.md5(registerUser.getPassword()));//工具类MD5加密密码

            result = userService.register(registerUser);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            EmailUtils.sendEmail(registerUser);
            //注册成功转发成功页面
            return "forward:/registerSuccess.jsp";
        }
        //失败转发到消息页面
        request.setAttribute("msg", "注册失败!");
        return "forward:/message.jsp";
    }

    /**
     * 邮箱激活
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = Base64Utils.decode(req.getParameter("c"));

        User user = userService.checkCode(code);

        if(user == null){
            req.setAttribute("msg", "激活失败");
            return "forward:/message.jsp";
        }

        if (user.getState() == 0) {
            userService.changeState(user);
        }
        req.setAttribute("msg", "您已激活,5秒后跳转登录...");
        resp.setHeader("refresh", "5;url=/login.jsp");
        return "forward:/message.jsp";


    }

    /**
     * 登录
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();


        //校验验证码
        String RealCode = (String) session.getAttribute("code");
        String loginCode = req.getParameter("code");

        //拿到验证码就可以销毁
        session.removeAttribute("code");

        if (null != RealCode) {
            if (!RealCode.equalsIgnoreCase(loginCode)) {

                req.setAttribute("msg", "登录失败!验证码错误!");
                return "forward:/login.jsp";
            }
        }

        //获取登录表单提交的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        password = MD5Utils.md5(password);
        //由于注册的时候MD5加密,传入对比之前也要加密
        User user = userService.login(username, password);

        if (user != null) {
            //登录成功,在session存储用户对象
            req.getSession().setAttribute("loginUser", user);

            //如果勾选了自动登录,存储用户cookie
            //勾选=on;不勾选=null
            String auto = req.getParameter("auto");
            if ("on".equals(auto)) {
                Cookie cookie = new Cookie("autoLoginUser", username + ":" + password);
                //设置cookie存活时间两周
                cookie.setMaxAge(60 * 60 * 24 * 14);
                resp.addCookie(cookie);
            }

            return "forward:/index.jsp";
        }

        req.setAttribute("msg", "登录失败!用户名或密码错误");
        return "forward:/login.jsp";
    }

    /**
     * 登出
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //注销时,设置自动登录的cookie的MaxAge为0,并重新发送给浏览器(覆盖清理cookie)
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        //移除存储的session
        req.getSession().removeAttribute("loginUser");
        return "forward:/index.jsp";
    }

    /**
     * 生成验证码
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void code(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //验证码工具类
        ValidateCode code = new ValidateCode(100, 50, 4, 4);
        code.createCode();
        //获得验证码存储到session,登录时验证
        req.getSession().setAttribute("code", code.getCode());
        //响应给浏览器
        code.write(resp.getOutputStream());
    }
}
