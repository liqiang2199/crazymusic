package com.music.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by empty cup on 2018/1/24.
 */

public class UtilsTools {
    /**
     * 正则表达式 判断电话号是否正确
     */
    public static boolean isPhoneNum(String num) {
        Pattern p =
                Pattern.compile("^(((13[0-9])|(14[0-9])|(15([0-3]|[5-9])|17[0,5-9])|(18[0,5-9])|(16[0,5-9])|(19[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{8})$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    public static boolean isStringNull(String s){
        if (s == null||s.equals(null)||s.equals("null")||s.equals("")){
            return true;
        }
        return false;
    }

    /**
     * 读取缓存的字符串
     * @param key
     * @return
     */
    public static String getReadCacheUtilData(String key){
        if (!isStringNull(key)){
            return "";
        }
        if (CacheUtil.contains(key)){
            return CacheUtil.get(key);
        }
        return "";

    }

}
