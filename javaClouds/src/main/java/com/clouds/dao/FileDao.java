package com.clouds.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Description TODO
 * @ClassName FileDao
 * @Author lly
 * @Date 2020/3/8 15:14
 * @Version V1.0
 */
public interface FileDao {
    //遍历用户目录中的所有文件存在ArrayList集合中返回
    ArrayList<File> findAllFiles(File userDirectory);
    //遍历用户目录中的所有类型的图片文件存放在ArrayList集合中返回
    ArrayList<File> findAllPictures(File userDirectory);
    //遍历用户目录中的所有类型的视频
    ArrayList<File> findAllVideos(File userDirectory);
    //遍历用户目录中的所有类型的音频
    ArrayList<File> findAllAudios(File userDirectory);
    //遍历用户目录中的所有类型的文档
    ArrayList<File> findAllDocumentations(File userDirectory);
   //通过文件名查询用户目录中的文件(模糊查询)
   ArrayList<File> findFileByFileName(String filename,File userDirectory);
   //查询用户目录的子集(如果File对象是目录标注为0，如果File对象是文件则标注为1)
   Map<File,Integer> findFiles(File userDirectory);
   //在指定文件夹下面创建文件夹
   void createDirectory(String filePath,String fileName);
   //删除指定的文件夹或文件
    void deleteFile(String filePath);
}
