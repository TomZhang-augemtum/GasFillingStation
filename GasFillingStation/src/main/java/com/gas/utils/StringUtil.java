package com.gas.utils;

public class StringUtil {
    public static boolean isEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
