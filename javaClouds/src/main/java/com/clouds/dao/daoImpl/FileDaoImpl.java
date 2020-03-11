package com.clouds.dao.daoImpl;

import com.clouds.dao.FileDao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/8 15:14
 * @Version V1.0
 */
public class FileDaoImpl implements FileDao {
    //遍历用户目录中的所有文件存在在ArrayList集合中返回
    @Override
    public ArrayList<File> findAllFiles(File userDirectory) {
        ArrayList<File> list = new ArrayList<>();
        File[] files = userDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                list.addAll(findAllFiles(files[i]));
            } else if (files[i].isFile()) {
                list.add(files[i]);
            }
        }
        return list;
    }

    //遍历用户目录中的所有类型的图片文件存放在ArrayList集合中返回
    @Override
    public ArrayList<File> findAllPictures(File userDirectory) {
        ArrayList<File> list = new ArrayList<>();
        File[] files = userDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                list.addAll(findAllPictures(files[i]));
            } else if (files[i].isFile()) {
                String name = files[i].getName();
                if (name.indexOf(".webp") != -1 || name.indexOf(".bmp") != -1
                        || name.indexOf(".pcx") != -1 || name.indexOf(".tif") != -1
                        || name.indexOf(".gif") != -1 || name.indexOf(".jepg") != -1
                        || name.indexOf(".tga") != -1 || name.indexOf(".exif") != -1
                        || name.indexOf(".fpx") != -1 || name.indexOf(".svg") != -1
                        || name.indexOf(".psd") != -1 || name.indexOf(".cdr") != -1
                        || name.indexOf(".pcd") != -1 || name.indexOf(".dxf") != -1
                        || name.indexOf(".ufo") != -1 || name.indexOf(".eps") != -1
                        || name.indexOf(".al") != -1 || name.indexOf(".png") != -1
                        || name.indexOf(".hdri") != -1 || name.indexOf(".raw") != -1
                        || name.indexOf(".wmf") != -1 || name.indexOf(".flic") != -1
                        || name.indexOf(".emf") != -1 || name.indexOf(".ico") != -1
                        || name.indexOf(".jpg") != -1) {
                    list.add(files[i]);
                }
            }
        }
        return list;
    }

    //遍历用户目录中的所有类型的视频
    @Override
    public ArrayList<File> findAllVideos(File userDirectory) {
        ArrayList<File> list = new ArrayList<>();
        File[] files = userDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                list.addAll(findAllVideos(files[i]));
            } else if (files[i].isFile()) {
                String name = files[i].getName();
                if (name.indexOf(".mp4") != -1 || name.indexOf(".flv") != -1
                        || name.indexOf(".f4v") != -1 || name.indexOf(".webm") != -1
                        || name.indexOf(".m4v") != -1 || name.indexOf(".mov") != -1
                        || name.indexOf(".3gp") != -1 || name.indexOf(".3g2") != -1
                        || name.indexOf(".rm") != -1 || name.indexOf(".rmvb") != -1
                        || name.indexOf(".wmv") != -1 || name.indexOf(".avi") != -1
                        || name.indexOf(".asf") != -1 || name.indexOf(".mpg") != -1
                        || name.indexOf(".mpeg") != -1 || name.indexOf(".mpe") != -1
                        || name.indexOf(".ts") != -1 || name.indexOf(".div") != -1
                        || name.indexOf(".dv") != -1 || name.indexOf(".divx") != -1
                        || name.indexOf(".vob") != -1 || name.indexOf(".dat") != -1
                        || name.indexOf(".mkv") != -1 || name.indexOf(".lavf") != -1
                        || name.indexOf(".cpk") != -1 || name.indexOf(".dirac") != -1
                        || name.indexOf(".ram") != -1 || name.indexOf(".qt") != -1
                        || name.indexOf(".fli") != -1 || name.indexOf(".flc") != -1
                        || name.indexOf(".mod") != -1) {
                    list.add(files[i]);
                }
            }
        }
        return list;
    }

    //遍历用户目录中的所有类型的音频
    @Override
    public ArrayList<File> findAllAudios(File userDirectory) {
        ArrayList<File> list = new ArrayList<>();
        File[] files = userDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                list.addAll(findAllAudios(files[i]));
            } else if (files[i].isFile()) {
                String name = files[i].getName();
                if (name.indexOf(".wav") != -1 || name.indexOf(".aif") != -1
                        || name.indexOf(".aiff") != -1 || name.indexOf(".au") != -1
                        || name.indexOf(".mp1") != -1 || name.indexOf(".mp2") != -1
                        || name.indexOf(".mp3") != -1 || name.indexOf(".ra") != -1
                        || name.indexOf(".rm") != -1 || name.indexOf(".ram") != -1
                        || name.indexOf(".mid") != -1 || name.indexOf(".rmi") != -1
                        || name.indexOf(".mod") != -1 || name.indexOf(".s3m") != -1
                        || name.indexOf(".xm") != -1 || name.indexOf(".mtm") != -1
                        || name.indexOf(".far") != -1 || name.indexOf(".kar") != -1
                        || name.indexOf(".it") != -1) {
                    list.add(files[i]);
                }
            }
        }
        return list;
    }

    //遍历用户目录中的所有类型的文档
    @Override
    public ArrayList<File> findAllDocumentations(File userDirectory) {
        ArrayList<File> list = new ArrayList<>();
        File[] files = userDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                list.addAll(findAllDocumentations(files[i]));
            } else if (files[i].isFile()) {
                String name = files[i].getName();
                if (name.indexOf(".xls") != -1 || name.indexOf(".doc") != -1
                        || name.indexOf(".ppt") != -1 || name.indexOf(".txt") != -1
                        || name.indexOf(".mdb") != -1 || name.indexOf(".htm") != -1
                        || name.indexOf(".xml") != -1) {
                    list.add(files[i]);
                }
            }
        }
        return list;
    }

    //通过文件名查询用户目录中的文件(模糊查询)
    @Override
    public ArrayList<File> findFileByFileName(String filename, File userDirectory) {
        ArrayList<File> list = new ArrayList<>();
        File[] files = userDirectory.listFiles();
        File file = null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                list.addAll(findFileByFileName(filename, files[i]));
            } else if (files[i].isFile()) {
                if (files[i].getName().indexOf(filename) != -1) {
                    list.add(files[i]);
                }
            }
        }
        return list;
    }

    //查询用户目录的子集(如果File对象是目录标注为0，如果File对象是文件则标注为1)
    @Override
    public Map<File, Integer> findFiles(File userDirectory) {
        Map<File, Integer> map = new HashMap<>();
        File[] files = userDirectory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                map.put(file, 0);
            } else if (file.isFile()) {
                map.put(file, 1);
            }
        }
        return map;
    }

    //在指定路径下面创建文件夹
    @Override
    public void createDirectory(String filePath, String fileName) {
        String path = filePath + File.separator + fileName;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    //删除指定的文件夹或文件
    @Override
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        } else {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteFile(files[i].getAbsolutePath());
                    } else if (files[i].isFile()) {
                        files[i].delete();
                    }
                }
            }
            file.delete();
        }
    }
}
