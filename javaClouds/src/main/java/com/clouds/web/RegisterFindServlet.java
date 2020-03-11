package com.clouds.web;

import com.alibaba.fastjson.JSON;
import com.clouds.domain.User;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registerFindUserServlet")
public class RegisterFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        Map<String,Object> map = new HashMap<>();
        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUserName(username);
        if(user != null){
            //{userExist:true,msg:'该用户已经存在'}
            map.put("userExist",true);
            map.put("msg","用户已存在");
        }else {
            //{userExist:false,msg:'注册成功'}
            map.put("userExist",false);
            map.put("msg","用户名正确");
        }
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(JSON.toJSONString(map));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
