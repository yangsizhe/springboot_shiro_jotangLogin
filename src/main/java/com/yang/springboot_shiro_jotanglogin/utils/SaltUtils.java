package com.yang.springboot_shiro_jotanglogin.utils;

import java.util.Random;
import java.util.UUID;

/*
生成盐的工具类
 */
public class SaltUtils {

    public static String generateSalt(String str){
//        //从"abcdefghijklmnopqrstuvwxyz"中随机取字符
//        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
//        //用StringBuilder或StringBuffer便于追加字符
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            char aChar = chars[new Random().nextInt(chars.length)];
//            stringBuilder.append(aChar);
//        }
//        //生成盐并返回
//        return stringBuilder.toString();


        //生成uuid
        UUID uuid = UUID.nameUUIDFromBytes(str.getBytes());
        //根据uuid生成盐
        return uuid.toString().substring(0,5);
    }

}
