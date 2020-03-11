package com.clouds.web;

import cn.hutool.captcha.ShearCaptcha;
import com.clouds.domain.User;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.UserServiceImpl;
import com.clouds.utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String vcode = req.getParameter("vcode");
        HttpSession session = req.getSession();
        ShearCaptcha captcha = (ShearCaptcha) session.getAttribute("captcha");
        session.removeAttribute("captcha");
        if (!captcha.verify(vcode)) {
            req.setAttribute("msg", "验证码错误");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } else {
            Map<String, String[]> parameterMap = req.getParameterMap();
            User user = CommonUtils.populate(new User(), parameterMap);
            UserService userService = new UserServiceImpl();
            User u = userService.findUser(user);
            if (u != null) {
                String remember = req.getParameter("remember");
                session.setAttribute("user", u);
                //如果选择记住用户则用户信息保存7天
                if ("1".equals(remember)) {
                    Cookie cookie = new Cookie("JSESSIONID", session.getId());
                    cookie.setMaxAge(60 * 60 * 24 * 7);   //时间单位秒
                    resp.addCookie(cookie);
                }
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                req.setAttribute("msg", "用户名或密码错误");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
            }
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
