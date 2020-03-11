package com.clouds.serviceTest;

import com.clouds.service.FileService;
import com.clouds.service.serviceImpl.FileServiceImpl;
import org.junit.Test;

import java.io.File;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/10 9:00
 * @Version V1.0
 */
public class createFile {
    @Test
    public void createFile(){
        FileService fileService = new FileServiceImpl();
        fileService.createUserRepository("c:\\aa","aa");
        fileService.createUserCache("c:\\aa","bb");
        File file = new File("c:\\aa\\aa");
        System.out.println(file.exists());
    }
}
