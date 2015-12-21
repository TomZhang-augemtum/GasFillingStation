package com.gas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static Date parseFromString(String str) {
        str = str.replace(" 0800 (中国标准时间)", "+08:00");
        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z", Locale.ENGLISH);
        Date begin = null;
        try {
            begin = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return begin;
    }

    public static String parseToLocal(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String parseToLocal(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
