package com.czx.yimem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateUtil {
    /**
     * 把字符串转化为Date
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        if (dateStr == null || "".equals (dateStr)) {
            return null;
        }

        SimpleDateFormat format = null;
        if (Pattern.matches ("\\d{4}-\\d{1,2}-\\d{1,2}", dateStr)) {
            format = new SimpleDateFormat ("yyyy-MM-dd");
        } else if (Pattern.matches ("\\d{4}\\d{2}\\d{2}", dateStr)) {
            format = new SimpleDateFormat ("yyyyMMdd");
        } else if (Pattern.matches ("\\d{4}年\\d{2}月\\d{2}日", dateStr)) {
            format = new SimpleDateFormat ("yyyy年MM月dd日", Locale.CHINA);
        } else if (Pattern.matches ("\\d{4}年\\d{1,2}月\\d{1,2}日", dateStr)) {
            format = new SimpleDateFormat ("yyyy年M月d日", Locale.CHINA);
        } else if (Pattern.matches ("\\d{1,2}\\w{3}\\d{4}", dateStr)) {
            format = new SimpleDateFormat ("dMMMyyyy", Locale.ENGLISH);
        } else if (Pattern.matches ("\\d{1,2}-\\w{3}-\\d{4}", dateStr)) {
            format = new SimpleDateFormat ("d-MMM-yyyy", Locale.ENGLISH);
        } else if (dateStr.length ( ) > 20) {
            format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss:SSS");
        } else {
            format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        }

        try {
            return format.parse (dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ( );
        }
        return null;
    }

    /**
     * 取得后一天的时间字
     *
     * @param dateStr
     * @return
     */
    public static Date getDayAfter(String dateStr, int dayCnt) throws Exception {
        return getDayAfter (parseDate (dateStr), dayCnt);
    }

    /**
     * 取得后一天的时间
     *
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date, int dayCnt) {
        GregorianCalendar cal = new GregorianCalendar ( );
        cal.setTime (date);
        cal.add (Calendar.DATE, dayCnt);
        return cal.getTime ( );
    }

    /**
     * 算出俩个时间，所间隔的多少天
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance ( );
        fromCalendar.setTime (startDate);
        fromCalendar.set (Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set (Calendar.MINUTE, 0);
        fromCalendar.set (Calendar.SECOND, 0);
        fromCalendar.set (Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance ( );
        toCalendar.setTime (endDate);
        toCalendar.set (Calendar.HOUR_OF_DAY, 0);
        toCalendar.set (Calendar.MINUTE, 0);
        toCalendar.set (Calendar.SECOND, 0);
        toCalendar.set (Calendar.MILLISECOND, 0);

        return (toCalendar.getTime ( ).getTime ( ) - fromCalendar.getTime ( ).getTime ( )) / (1000 * 60 * 60 * 24);
    }
}
