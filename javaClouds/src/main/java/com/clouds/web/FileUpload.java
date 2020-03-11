package com.clouds.web;

import com.clouds.domain.User;
import com.clouds.utils.CloudUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;    //序列号
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 10;  // 10MB        //内存阈值
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 50; // 50MB        //最大文件大小
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 60; // 60MB     //最大请求大小

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *  处理跨域请求问题：
         *  response.setHeader("Access-Control-Allow-Origin", "*");
         *  response.setHeader("Cache-Control","no-cache");
         *
         */
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Cache-Control", "no-cache");
        //设置字符集编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 配置上传参数   实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录（）
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        // 用以上工厂实例化上传组件
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        //设置用户的缓存文件夹
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String userCache = CloudUtils.getCache()+File.separator+user.getId();
        File file = new File(userCache);
        if(!file.exists()){
            file.mkdirs();
        }
        // 解析请求的内容提取文件数据
        @SuppressWarnings("unchecked")
        List<FileItem> formItems;
        try {
            formItems = upload.parseRequest(req);
            if (formItems != null && ((List) formItems).size() > 0) {
                String fileName = null;
                // 迭代表单数据，处理在表单中的信息
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        fileName = item.getString();
                    } else {
                        continue;
                    }
                }
                // 处理不在表单中的字段
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        if (fileName.equals(null) || fileName.equals("")) {
                            System.out.println("文件获取失败");
                            return;
                        } else {
                            String filePath = userCache + File.separator + fileName;
                            File storeFile = new File(filePath);
                            // 保存文件到硬盘
                            item.write(storeFile);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
