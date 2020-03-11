package com.clouds.web;

import com.clouds.domain.User;
import com.clouds.service.FileService;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.FileServiceImpl;
import com.clouds.service.serviceImpl.UserServiceImpl;
import com.clouds.utils.CloudUtils;
import com.clouds.utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = CommonUtils.populate(new User(), parameterMap);
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        User user1 = userService.findUserByUserName(user.getUsername());
        FileService fileService = new FileServiceImpl();
        //创建用户仓库
        fileService.createUserRepository(CloudUtils.getRepository(),user1.getId()+"");
        //创建用户缓存仓库
        fileService.createUserCache(CloudUtils.getCache(),user1.getId()+"");
        req.setAttribute("msg","注册成功，请登录");
        req.getRequestDispatcher("/register.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
