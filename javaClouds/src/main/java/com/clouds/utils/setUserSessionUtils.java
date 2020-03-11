package com.clouds.utils;

import com.clouds.domain.User;
import com.clouds.service.FileService;
import com.clouds.service.UserService;
import com.clouds.service.serviceImpl.FileServiceImpl;
import com.clouds.service.serviceImpl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/10 20:45
 * @Version V1.0
 */
public class setUserSessionUtils {
    public static void setUserUseStorageBySession(HttpSession session){
        User user = (User) session.getAttribute("user");
        File Myfile = new File(CloudUtils.getRepository() + File.separator + user.getId());
        FileService fileService = new FileServiceImpl();
        long length = fileService.findAllFilesSize(Myfile);
        UserService userService = new UserServiceImpl();
        userService.updateUserUseStorageByUser(user,length);
        User NewUser = userService.findUserByUserName(user.getUsername());
        session.removeAttribute("user");
        session.setAttribute("user",NewUser);
    }
}
