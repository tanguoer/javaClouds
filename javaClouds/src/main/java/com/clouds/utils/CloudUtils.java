package com.clouds.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/8 15:29
 * @Version V1.0
 */
//系统初始化配置
public class CloudUtils {
    public static double maxStorage;    //用户最大存储量 1G
    public static double useStorage;    //用户初始使用的存储量 0
    public static String repository;    //系统设定的所有用户存放文件的所在位置
    public static String cache;    //系统设定的所有用户存放缓存文件的所在位置
    static {
        Properties prop = new Properties();
        InputStream in = CloudUtils.class.getClassLoader().getResourceAsStream("cloud.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        maxStorage = Double.valueOf(prop.getProperty("MaxStorage"));
        useStorage = Double.valueOf(prop.getProperty("UseStorage"));
        repository = prop.getProperty("repository");
        cache = prop.getProperty("cache");
    }

    public static double getMaxStorage() {
        return maxStorage;
    }

    public static double getUseStorage() {
        return useStorage;
    }

    public static String getRepository() {
        return repository;
    }

    public static String getCache() {
        return cache;
    }
}
