package com.jun.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class JsonUtil {
    public static Map<String, Object> getMapFromJson(String jsonString) {
        if (checkStringIsEmpty(jsonString)) {
            return null;
        }
        return JSON.parseObject(jsonString);
    }
    private static boolean checkStringIsEmpty(String str) {
        if (str == null || str.trim().equals("") || str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }
}