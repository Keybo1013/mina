package com.keybo.mina.server.protocol.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Chen guiting
 * Date: 16-5-9
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {
    public static String format1 = "yyyy-MM-dd HH:mm:ss";
    public static String format2 = "yyyy-MM-dd HH:mm";
    public static String format3 = "yyyy-MM-dd";


    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 日期转换为字符串
     *
     * @return
     */
    public static String getCurrDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format1);
        return simpleDateFormat.format(getCurrDate());
    }

    /**
     * 日期转换为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateToStr(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取两个时间中的最大时间
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Date getMaxDate(Date d1, Date d2) {
        return (d1.getTime() > d2.getTime()) ? d1 : d2;
    }

    /**
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date getDate(int year, int month, int day, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        return calendar.getTime();
    }

}
