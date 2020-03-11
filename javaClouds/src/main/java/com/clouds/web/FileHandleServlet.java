package com.clouds.web;

import com.clouds.domain.User;
import com.clouds.service.FileService;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.FileServiceImpl;
import com.clouds.service.serviceImpl.UserServiceImpl;
import com.clouds.utils.CloudUtils;
import com.clouds.utils.setUserSessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@WebServlet("/fileHandleServlet")
public class FileHandleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String filename = req.getParameter("filename");
        String path = req.getParameter("path");
        String filepath = path.replace("%2F",File.separator);
        FileService fileService = new FileServiceImpl();
        if("delete".equals(action)){
            fileService.deleteFile(filepath);
            //修改用户session(更新用户使用空间容量)
            setUserSessionUtils.setUserUseStorageBySession(req.getSession());
            req.getRequestDispatcher("/FileSearchServlet").forward(req,resp);
        }
        if("download".equals(action)){
            resp.setContentType("application/multipart/form-data"); //设置文件ContentType类型，这样设置，会自动判断下载文件类型
            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));   //设置下载方式和文件名
            File file = new File(filepath);
            InputStream in = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(in);      //读取服务器中的文件
            BufferedOutputStream ous = new BufferedOutputStream(resp.getOutputStream());    //向客户端响应流文件
            byte[] bytes = new byte[1024];
            while (bis.read(bytes) != -1) {
                ous.write(bytes);
                ous.flush();
            }
            ous.close();
            bis.close();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
