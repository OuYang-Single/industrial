package com.ijcsj.common_library.util;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;

import androidx.core.net.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    // 日期格式年份，例如：2022，2023
    public static final String FORMAT_YYYY = "yyyy";
    // 其他格式常量...

    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getDefault();
    private static TimeZone DefaultTimeZone = TimeZone.getDefault();

    /**
     * 获取当前时间的字符串表示
     *
     * @param pattern 时间格式
     * @return 当前时间的字符串表示
     */
    public static String getCurrentDate(String pattern) {
        return formatToStr(new Date(), pattern);
    }

    /**
     * 将时间戳格式化为指定格式的字符串
     *
     * @param timestamp 时间戳
     * @param pattern   时间格式
     * @return 格式化后的时间字符串
     */
    public static String formatToStr(long timestamp, String pattern) {
        return formatToStr(new Date(timestamp), pattern);
    }

    /**
     * 将日期对象格式化为指定格式的字符串
     *
     * @param date    日期对象
     * @param pattern 时间格式
     * @return 格式化后的时间字符串
     */
    public static String formatToStr(Date date, String pattern) {
        DateFormat dateFormat = getDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 获取指定格式的日期格式化对象
     *
     * @param pattern 时间格式
     * @return 日期格式化对象
     */
    private static DateFormat getDateFormat(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(DEFAULT_TIMEZONE);
        return dateFormat;
    }

    /**
     * 格式化字符串时间为指定格式
     *
     * @param dateString 字符串时间
     * @param format     格式
     * @return 格式化后的时间字符串
     */
    public static String formatStringDate(String dateString, String format) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat(format);
        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前时间的日期对象
     *
     * @return 当前时间的日期对象
     */
    public static Date getCurrentTime() {
        return new Date();
    }

    public static  boolean isSameDay(long timestamp1,long timestamp2){
        Date date1=new Date(timestamp1);
        Date date2=new Date(timestamp2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String day1=sdf.format(date1);
        String day2=sdf.format(date1);
        return day1.equals(day2);
    }
    /**
     * 将日期对象格式化为指定格式的时间字符串
     *
     * @param date    日期对象
     * @param pattern 时间格式
     * @return 格式化后的时间字符串
     */
    public static String formatTime(Date date, String pattern) {
       final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    public static String formatTimes(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        final SimpleDateFormat sdfd = new SimpleDateFormat("MM-dd");
        return sdf.format(date) + "\n" +sdfd.format(date);
    }
    /**
     * 解析指定格式的时间字符串为日期对象
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 解析后的日期对象
     * @throws ParseException 解析异常
     */
    public static Date parseTime(String time, String pattern) throws ParseException {
        final  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(time);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算两个日期之间的时间差，返回指定时间单位的差值
     *
     * @param date1     第一个日期对象
     * @param date2     第二个日期对象
     * @param timeUnit  时间单位
     * @return 时间差的差值
     */
    public static long getTimeDifference(Date date1, Date date2, TimeUnit timeUnit) {
        long difference = date2.getTime() - date1.getTime();
        return timeUnit.convert(difference, TimeUnit.MILLISECONDS);
    }

    /**
     * 判断指定时间是否在给定时间区间内
     *
     * @param time      待判断的时间
     * @param startTime 时间区间的开始时间
     * @param endTime   时间区间的结束时间
     * @return 如果指定时间在时间区间内，返回 true；否则返回 false
     */
    public static boolean isInTimeRange(Date time, Date startTime, Date endTime) {
        return time.after(startTime) && time.before(endTime);
    }

    /**
     * 判断指定年份是否为闰年
     *
     * @param year 年份
     * @return 如果是闰年，返回 true；否则返回 false
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * 获取指定日期对象的年份
     *
     * @param date 日期对象
     * @return 年份
     */
    public static int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期对象的月份
     *
     * @param date 日期对象
     * @return 月份
     */
    public static int getMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定日期对象的星期
     *
     * @param date 日期对象
     * @return 星期，1 表示星期一，2 表示星期二，依次类推
     */
    public static int getWeekdayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static String getWeekdayFromDates(Date date) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeekString;
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                dayOfWeekString = "周日";
                break;
            case Calendar.MONDAY:
                dayOfWeekString = "周一";
                break;
            case Calendar.TUESDAY:
                dayOfWeekString = "周二";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeekString = "周三";
                break;
            case Calendar.THURSDAY:
                dayOfWeekString = "周四";
                break;
            case Calendar.FRIDAY:
                dayOfWeekString = "周五";
                break;
            case Calendar.SATURDAY:
                dayOfWeekString = "周六";
                break;
            default:
                dayOfWeekString = "";
                break;
        }

        return dayOfWeekString;
    }

    public static Calendar convertToTimeZone(Calendar date, String timeZoneId) {
        Calendar convertedDate = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
        convertedDate.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
        return convertedDate;
    }

    public static String getFormatMonthUS(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM", Locale.US);
        Date currentDate = new Date();
        String monthAbbrev = dateFormat.format(currentDate);
        return monthAbbrev;
    }
    public static String getFormatWeekNumUS(){
        SimpleDateFormat format = new SimpleDateFormat("EEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        String abbrev = format.format(calendar.getTime());
        return abbrev;
    }
    public static int is24HourFormat(Context context) {
        //获得内容提供者
        ContentResolver mResolver= context.getContentResolver();
        //获得系统时间制
        String timeFormat = android.provider.Settings.System.getString(mResolver,android.provider.Settings.System.TIME_12_24);
        //判断时间制
        if(timeFormat.equals("24"))
        {
          return 0;
        }else {
            //12小时制
            //获得日历
            Calendar mCalendar=Calendar.getInstance();
            if(mCalendar.get(Calendar.AM_PM)== Calendar.AM){
                //白天
                return 1;
            }else {
                return 2;
            }
        }
    }
    public static String getFormatDayMonth(){
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth+"";
    }
}


