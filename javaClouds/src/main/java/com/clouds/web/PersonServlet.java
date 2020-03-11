package com.clouds.web;

import com.clouds.domain.User;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.UserServiceImpl;
import com.clouds.utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/personServlet")
public class PersonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = CommonUtils.populate(new User(), parameterMap);
        UserService userService = new UserServiceImpl();
        userService.updateUserById(user);
        User NewUser = userService.findUserById(user.getId());
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("user", NewUser);
        request.setAttribute("msg1", "信息修改成功");
        request.getRequestDispatcher("/findUserServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
