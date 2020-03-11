package com.clouds.web;

import com.clouds.domain.FileInfo;
import com.clouds.domain.FilePage;
import com.clouds.domain.User;
import com.clouds.service.FileService;
import com.clouds.service.serviceImpl.FileServiceImpl;
import com.clouds.utils.CloudUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/FileSearchServlet")
public class FileSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String search = null;
        String fileType = req.getParameter("FileType");
        if ("5".equals(fileType)) {
            search = req.getParameter("search");
            if (search != null) {
                session.removeAttribute("search");
                session.setAttribute("search", search);
            } else {
                search = (String) session.getAttribute("search");
            }
        }
        String pageNum = req.getParameter("pageNum");
        String rows = req.getParameter("rows");
        String userRepository = CloudUtils.getRepository() + File.separator + user.getId();
        File file = new File(userRepository);
        FileService fileService = new FileServiceImpl();
        FilePage<FileInfo> page = fileService.FilePageQuery(rows, pageNum, file, fileType, search);
        req.setAttribute("FilePage", page);
        req.setAttribute("FileType", fileType);
        if ("6".equals(fileType)) {
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/search.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
