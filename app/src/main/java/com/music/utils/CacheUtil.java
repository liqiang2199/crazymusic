package com.music.utils;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

public class CacheUtil {
    public static void init(Context context) {
        Hawk.init(context).build();
    }
    public static void put(String key, int value) {
        Hawk.put(key, value);
    }
    public static void put(String key, double value) {
        Hawk.put(key, value);
    }
    public static void put(String key, float value) {
        Hawk.put(key, value);
    }
    public static void put(String key, String value) {
        Hawk.put(key, value);
    }
    public static void put(String key, Object value) {
        Hawk.put(key, value);
    }
    public static <T> T get(String key) {
        return Hawk.get(key);
    }
    public static void delete(String key) {
        Hawk.delete(key);
    }
    public static boolean contains(String key) {
        return Hawk.contains(key);
    }
    public static boolean deleteAll() {
        return Hawk.deleteAll();
    }
}
