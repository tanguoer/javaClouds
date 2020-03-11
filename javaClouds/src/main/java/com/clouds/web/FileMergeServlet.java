package com.clouds.web;

import com.clouds.domain.User;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.UserServiceImpl;
import com.clouds.utils.CloudUtils;
import com.clouds.utils.setUserSessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/fileMergeServlet")
public class FileMergeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Cache-Control", "no-cache");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //获取用户的缓存
        String userCache = CloudUtils.getCache() + File.separator + user.getId();
        //获取用户当前所在文件夹
        String userRepository = null;
        String currentFolder = req.getParameter("CurrentFolder");
        if (currentFolder != "" && currentFolder != null) {
            userRepository = currentFolder;
        } else {
            userRepository = CloudUtils.getRepository() + File.separator + user.getId();
        }
        File file = new File(userRepository);
        File file1 = new File(userCache);
        if (!file.exists()) {
            file.mkdirs();
        }
        if(!file1.exists()){
            file1.mkdirs();
        }
        //获取文件名称
        String filename = req.getParameter("filename").split("L")[1];
        //获取文件识别码
        String DocumentIdentificationCode = req.getParameter("filename").split("L")[0];
        //获取文件被切割数量(用于缓存是否读取完整)
        int index = Integer.valueOf(req.getParameter("index"));
        //遍历缓存文件夹下所有符合要求的文件
        File cache = new File(userCache);
        int count = 0;
        List<File> list;
        try {
            Thread.sleep(2000); //休眠2秒，防止空指针异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            list = new ArrayList<>();
            for (File listFile : cache.listFiles()) {
                if (listFile.getName().split("L")[0].equals(DocumentIdentificationCode)) {
                    count++;
                    list.add(listFile);
                }
            }
            if (count == index) {
                break;
            } else {
                count = 0;
            }
        }
        resp.getWriter().write("true");
        //对从缓存中得到的list集合重新排序
        list.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int num1 = Integer.valueOf(o1.getName().split("L")[1]);
                int num2 = Integer.valueOf(o2.getName().split("L")[1]);
                return num1 - num2;
            }
        });

        String filePath = userRepository + File.separator + System.currentTimeMillis() + filename;
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile, true));
        BufferedInputStream bis = null;
        //循环读取缓存文件放入保存文件中
        for (File file2 : list) {
            bis = new BufferedInputStream(new FileInputStream(new File(userCache + File.separator + file2.getName())));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
                bos.flush();
            }
        }
        if (bos != null) {
            bos.close();
        }
        if (bis != null) {
            bis.close();
        }
        //文件上传成功后回收资源
        //修改用户session(更新用户使用空间容量)
        setUserSessionUtils.setUserUseStorageBySession(session);
        for (File file2 : list) {
            boolean b = file2.delete();
            if (!b) {
                System.gc();    //回收资源
                file2.delete();
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
