package com.clouds.service;

import com.clouds.domain.FileInfo;
import com.clouds.domain.FilePage;

import java.io.File;

/**
 * @Description TODO
 * @ClassName FileService
 * @Author lly
 * @Date 2020/3/9 8:59
 * @Version V1.0
 */
public interface FileService {
    //文件list集合的分页查询
    FilePage<FileInfo> FilePageQuery(String rowNum, String pageNums, File userDirectory, String FileType, String filename);
    //创建用户仓库
    void createUserRepository(String filePath,String RepositoryName);
    //创建用户缓存仓库
    void createUserCache(String filePath,String CacheName);

    //删除指定文件夹或文件
    void deleteFile(String filepath);

    //获取指定文件夹大小
    long findAllFilesSize(File myfile);
}
