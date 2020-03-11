package com.clouds.utils;

import com.clouds.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Description TODO
 * @Author lly
 * @Date 2020/3/6 14:04
 * @Version V1.0
 */
public class CommonUtils {
    public static User populate(User user, Map<String, String[]> parameterMap){
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return user;
    }
}
