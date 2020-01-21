package com.massadobe.attempt.common.utils;

import java.util.Random;

/**
 * @ClassName: CommonUtils
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 14:44
 * @Version: 1.0.0
 * @param: * @param null
 */
public class CommonUtils {

    /**
     * @Method: getRandomString
     * @Author: MassAdobe
     * @Email: massadobe8@gmail.com
     * @Description: 生成 a->b A->B 0->9 的入参位数随机字符串
     * @Date: Created in 2019-12-13 14:44
     * @Version: 1.0.0
     * @param: * @param null
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
