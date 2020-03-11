package com.clouds.service.serviceImpl;

import com.clouds.dao.FileDao;
import com.clouds.dao.daoImpl.FileDaoImpl;
import com.clouds.domain.FileInfo;
import com.clouds.domain.FilePage;
import com.clouds.service.FileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/9 8:59
 * @Version V1.0
 */
public class FileServiceImpl implements FileService {

    private FileDao fileDao = new FileDaoImpl();
    //文件list集合的分页查询
    @Override
    public FilePage<FileInfo> FilePageQuery(String rowNum, String pageNums,File userDirectory,String FileType,String filename) {
        //判断查询类型(1:图片；2：视频；3：文档；4：音频；5：模糊查询；6：查询所有文件)
        ArrayList<File> list = null;
        ArrayList<FileInfo> filelist = new ArrayList<>();
        if("1".equals(FileType)){
            list = fileDao.findAllPictures(userDirectory);
        }else if("2".equals(FileType)){
            list = fileDao.findAllVideos(userDirectory);
        }else if("3".equals(FileType)){
            list = fileDao.findAllDocumentations(userDirectory);
        }else if("4".equals(FileType)){
            list = fileDao.findAllAudios(userDirectory);
        }else if("5".equals(FileType)){
            list = fileDao.findFileByFileName(filename,userDirectory);
        }else if("6".equals(FileType)){
            list = fileDao.findAllFiles(userDirectory);
        }else {
            return null;
        }
        for (File file : list) {
            //此处对文件路径进行了替换
            FileInfo fileInfo = new FileInfo(file.getName(), file.getAbsolutePath().replace(File.separator,"%2F"), file.length());
            filelist.add(fileInfo);
        }
        //获取每页显示记录数
        int rows = Integer.valueOf(rowNum);
        //获取当前页号
        int pageNum = Integer.valueOf(pageNums);
        if(pageNum<1){
            pageNum = 1;
        }
        //总记录数
        int totalCount = filelist.size();

        //总页数
        int totalPage = (totalCount%rows) == 0 ? (totalCount/rows) : (totalCount/rows)+1;
        if(pageNum>totalPage){
            pageNum = totalPage;
        }
        //分页查询起始下标(集合下标都是从0开始的)
        int start = (pageNum -1)*rows;
        if(start<0){
            start = 0;
        }
        //分页查询结束下标
        int end = (start+rows)<totalCount ? (start+rows+1):totalCount;
        //获取当前页显示数据
        List<FileInfo> files = filelist.subList(start, end);
        FilePage<FileInfo> page = new FilePage<>(totalCount,totalPage,files,pageNum,rows);
        return page;
    }

    //创建用户仓库
    @Override
    public void createUserRepository(String filePath, String RepositoryName) {
        fileDao.createDirectory(filePath,RepositoryName);
    }

    //创建缓存仓库
    @Override
    public void createUserCache(String filePath, String CacheName) {
        fileDao.createDirectory(filePath,CacheName);
    }

    //删除指定文件夹或文件
    @Override
    public void deleteFile(String filepath) {
        fileDao.deleteFile(filepath);
    }

    //获取指定文件夹大小
    @Override
    public long findAllFilesSize(File myfile) {
        ArrayList<File> allFiles = fileDao.findAllFiles(myfile);
        long length = 0L;
        for (File allFile : allFiles) {
            length+=allFile.length();
        }
        return length;
    }
}
