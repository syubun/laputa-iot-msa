package com.laputa.iot.common.core.util;


import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：日期工具类
 *
 */
@Slf4j
public class DateUtils {
    /**
     * 默认使用系统当前时区 ZoneId.systemDefault()
     */
    public static final ZoneId ZONE = ZoneId.of("Asia/Shanghai");
    public final static String DEFAULT_YEAR_FORMAT = "yyyy";
    public final static String DEFAULT_MONTH_FORMAT = "yyyy-MM";
    public final static String DEFAULT_MONTH_FORMAT_SLASH = "yyyy/MM";
    public final static String DEFAULT_MONTH_FORMAT_EN = "yyyy年MM月";
    public final static String DEFAULT_WEEK_FORMAT = "yyyy-ww";
    public final static String DEFAULT_WEEK_FORMAT_EN = "yyyy年ww周";
    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public final static String DEFAULT_DATE_FORMAT_EN = "yyyy年MM月dd日";
    public final static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public final static String DAY = "DAY";
    public final static String MONTH = "MONTH";
    public final static String WEEK = "WEEK";
    public static final String DATE_FORMAT_DS = "yyyyMMdd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 一个月平均天数
     */
    public final static long MAX_MONTH_DAY = 30;
    /**
     * 3个月平均天数
     */
    public final static long MAX_3_MONTH_DAY = 90;
    /**
     * 一年平均天数
     */
    public final static long MAX_YEAR_DAY = 365;


    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;

    private static final String ONE_SHORT_AGO = "刚刚";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";



    public DateUtils() {
    }
//--格式化日期start-----------------------------------------

    /**
     * 格式化日期,返回格式为 yyyy-MM
     *
     * @param date 日期
     * @return
     */
    public static String format(LocalDateTime date, String pattern) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        if (pattern == null) {
            pattern = DEFAULT_MONTH_FORMAT;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     *method 将字符串类型的日期转换为一个Date（java.sql.Date）
     dateString 需要转换为Date的字符串
     dataTime Date
     */
    public final static Date string2Date(String dateString) throws ParseException {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        dateFormat.setLenient(false);
        java.util.Date timeDate = dateFormat.parse(dateString);//util类型
        java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
        return dateTime;
    }



    /**
     * 根据传入的格式格式化日期.默认格式为MM月dd日
     *
     * @param d 日期
     * @param f 格式
     * @return
     */
    public static String format(Date d, String f) {
        Date date = d;
        String format = f;
        if (date == null) {
            date = new Date();
        }
        if (format == null) {
            format = DEFAULT_DATE_TIME_FORMAT;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 yyyy-MM-dd
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDate(LocalDateTime date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    public static String formatAsDateEn(LocalDateTime date) {
        return format(date, DEFAULT_DATE_FORMAT_EN);
    }


    public static String formatAsYearMonth(LocalDateTime date) {
        return format(date, DEFAULT_MONTH_FORMAT);
    }

    public static String formatAsYearMonthEn(LocalDateTime date) {
        return format(date, DEFAULT_MONTH_FORMAT_EN);
    }
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int TIME_DAY = 24 * HOUR;
//    public static void main(String[] args) {
//        long ms = 4004543l;
//        System.out.println(DateUtils.getDhmm(ms));
//    }

    //    long ms = 10304004543l;
    public static String getDhmm(long ms) {
        StringBuffer text = new StringBuffer("");
        if (ms > TIME_DAY){
            text.append(ms / TIME_DAY).append("天 ");
            ms %= TIME_DAY;
        }
        if (ms > HOUR){
            text.append(ms / HOUR).append("时 ");
            ms %= HOUR;
        }
        if (ms > MINUTE){
            text.append(ms / MINUTE).append("分 ");
            ms %= MINUTE;
        }
        if (ms > SECOND){
            text.append(ms / SECOND).append("秒");
//            ms %= SECOND;
        }
//        text.append(ms + " ms");
        return text.toString();
    }
    /**
     * 格式化日期,返回格式为 yyyy-ww
     *
     * @param date 日期
     * @return
     */
    public static String formatAsYearWeek(LocalDateTime date) {
        return format(date, DEFAULT_WEEK_FORMAT);
    }

    public static String formatAsYearWeekEn(LocalDateTime date) {
        return format(date, DEFAULT_WEEK_FORMAT_EN);
    }

    /**
     * 格式化日期,返回格式为 yyyy-MM
     *
     * @param date 日期
     * @return
     */
    public static String formatAsYearMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_MONTH_FORMAT);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 yyyy-ww
     *
     * @param date 日期
     * @return
     */
    public static String formatAsYearWeek(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_WEEK_FORMAT);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 HH:mm:ss 例:12:24:24
     *
     * @param date 日期
     * @return
     */
    public static String formatAsTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 yyyy-MM-dd
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        return df.format(date);
    }

    /**
     * 格式化日期,返回格式为 dd ,即对应的天数.
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd");
        return df.format(date);
    }

    //--格式化日期end-----------------------------------------

    //--解析日期start-----------------------------------------

    /**
     * 将字符转换成日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parse(String dateStr, String format) {
        Date date = null;
        SimpleDateFormat sdateFormat = new SimpleDateFormat(format);
        sdateFormat.setLenient(false);
        try {
            date = sdateFormat.parse(dateStr);

        } catch (Exception e) {
            log.info("DateUtils error {} ", e);
        }
        return date;
    }

    /**
     * 根据传入的String返回对应的date
     *
     * @param dateString
     * @return
     */
    public static Date parseAsDate(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 按给定参数返回Date对象
     *
     * @param dateTime 时间对象格式为("yyyy-MM-dd HH:mm:ss");
     * @return
     */
    public static Date parseAsDateTime(String dateTime) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        try {
            return simpledateformat.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取指定日期的开始时间
     * 如：00:00:00
     *
     * @param value
     * @return
     */
    public static Date getDate0000(LocalDateTime value) {
        return getDate0000(value.toLocalDate());
    }

    /**
     * 获取指定日期的开始时间
     * 如：00:00:00
     *
     * @param value
     * @return
     */
    public static Date getDate0000(Date value) {
        return getDate0000(DateUtils.date2LocalDate(value));
    }

    /**
     * 获取指定日期的开始时间
     * 如：00:00:00
     *
     * @param value
     * @return
     */
    public static Date getDate0000(LocalDate value) {
        LocalDateTime todayStart = LocalDateTime.of(value, LocalTime.MIN);
        return DateUtils.localDateTime2Date(todayStart);
    }

    /**
     * 获取指定日期的结束时间
     * 如：23:59:59
     *
     * @param value
     * @return
     */
    public static Date getDate2359(LocalDateTime value) {
        return getDate2359(value.toLocalDate());

    }

    /**
     * 获取指定日期的结束时间
     * 如：23:59:59
     *
     * @param value
     * @return
     */
    public static Date getDate2359(Date value) {
        return getDate2359(DateUtils.date2LocalDate(value));
    }

    /**
     * 获取指定日期的结束时间
     * 如：23:59:59
     *
     * @param value
     * @return
     */
    public static Date getDate2359(LocalDate value) {
        LocalDateTime dateEnd = LocalDateTime.of(value, LocalTime.MAX);
        return DateUtils.localDateTime2Date(dateEnd);
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    //--解析日期 end-----------------------------------------


    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        if (date == null) {
            return LocalDateTime.now();
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 日期转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date) {
        if (date == null) {
            return LocalDate.now();
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 日期转 LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime date2LocalTime(Date date) {
        if (date == null) {
            return LocalTime.now();
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalTime();
    }

    //-计算日期 start------------------------------------------


    /**
     * 计算结束时间与当前时间中的天数
     *
     * @param endDate 结束日期
     * @return
     */
    public static long until(Date endDate) {
        return LocalDateTime.now().until(date2LocalDateTime(endDate), ChronoUnit.DAYS);
    }

    /**
     * 计算结束时间与开始时间中的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static long until(Date startDate, Date endDate) {
        return date2LocalDateTime(startDate).until(date2LocalDateTime(endDate), ChronoUnit.DAYS);
    }


    /**
     * 计算结束时间与开始时间中的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static long until(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    public static long until(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 计算2个日期之间的所有的日期 yyyy-MM-dd
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenDay(Date start, Date end) {
        return getBetweenDay(date2LocalDate(start), date2LocalDate(end));
    }

    /**
     * 计算2个日期之间的所有的日期 yyyy-MM-dd
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenDay(String start, String end) {
        return getBetweenDay(LocalDate.parse(start), LocalDate.parse(end));
    }

    /**
     * 计算2个日期之间的所有的日期 yyyy-MM-dd
     * 含头含尾
     *
     * @param startDate yyyy-MM-dd
     * @param endDate   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenDay(LocalDate startDate, LocalDate endDate) {
        return getBetweenDay(startDate, endDate, DEFAULT_DATE_FORMAT);
    }

    public static List<String> getBetweenDayEn(LocalDate startDate, LocalDate endDate) {
        return getBetweenDay(startDate, endDate, DEFAULT_DATE_FORMAT_EN);
    }

    public static List<String> getBetweenDay(LocalDate startDate, LocalDate endDate, String pattern) {
        if (pattern == null) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        List<String> list = new ArrayList<>();
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        String finalPattern = pattern;
        Stream.iterate(startDate, d -> d.plusDays(1)).
                limit(distance + 1)
                .forEach(f -> list.add(f.format(DateTimeFormatter.ofPattern(finalPattern))));
        return list;
    }


    /**
     * 计算2个日期之间的所有的周 yyyy-ww
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenWeek(Date start, Date end) {
        return getBetweenWeek(date2LocalDate(start), date2LocalDate(end));
    }

    /**
     * 计算2个日期之间的所有的周 yyyy-ww
     * 含头含尾
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenWeek(String start, String end) {
        return getBetweenWeek(LocalDate.parse(start), LocalDate.parse(end));
    }

    /**
     * 计算2个日期之间的所有的周 yyyy-ww
     * 含头含尾
     *
     * @param startDate yyyy-MM-dd
     * @param endDate   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenWeek(LocalDate startDate, LocalDate endDate) {
        return getBetweenWeek(startDate, endDate, DEFAULT_WEEK_FORMAT);
    }

    public static List<String> getBetweenWeek(LocalDate startDate, LocalDate endDate, String pattern) {
        List<String> list = new ArrayList<>();

        long distance = ChronoUnit.WEEKS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusWeeks(1)).
                limit(distance + 1).forEach(f -> list.add(f.format(DateTimeFormatter.ofPattern(pattern))));
        return list;
    }

    /**
     * 计算2个日期之间的所有的月 yyyy-MM
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenMonth(Date start, Date end) {
        return getBetweenMonth(date2LocalDate(start), date2LocalDate(end));
    }

    /**
     * 计算2个日期之间的所有的月 yyyy-MM
     *
     * @param start yyyy-MM-dd
     * @param end   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenMonth(String start, String end) {
        return getBetweenMonth(LocalDate.parse(start), LocalDate.parse(end));
    }

    /**
     * 计算2个日期之间的所有的月 yyyy-MM
     *
     * @param startDate yyyy-MM-dd
     * @param endDate   yyyy-MM-dd
     * @return
     */
    public static List<String> getBetweenMonth(LocalDate startDate, LocalDate endDate) {
        return getBetweenMonth(startDate, endDate, DEFAULT_MONTH_FORMAT);
    }

    public static List<String> getBetweenMonth(LocalDate startDate, LocalDate endDate, String pattern) {
        List<String> list = new ArrayList<>();
        long distance = ChronoUnit.MONTHS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }

        Stream.iterate(startDate, d -> d.plusMonths(1))
                .limit(distance + 1)
                .forEach(f -> list.add(f.format(DateTimeFormatter.ofPattern(pattern))));
        return list;
    }

    /**
     * 计算时间区间内的日期列表，并返回
     *
     * @param startTime
     * @param endTime
     * @param dateList
     * @return
     */
    public static String calculationEn(LocalDateTime startTime, LocalDateTime endTime, List<String> dateList) {
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }
        if (endTime == null) {
            endTime = LocalDateTime.now().plusDays(30);
        }
        return calculationEn(startTime.toLocalDate(), endTime.toLocalDate(), dateList);
    }

    public static String calculation(LocalDate startDate, LocalDate endDate, List<String> dateList) {
        if (startDate == null) {
            startDate = LocalDate.now();
        }
        if (endDate == null) {
            endDate = LocalDate.now().plusDays(30);
        }
        if (dateList == null) {
            dateList = new ArrayList<>();
        }
        long day = until(startDate, endDate);

        String dateType = MONTH;
        if (day >= 0 && day <= MAX_MONTH_DAY) {
            dateType = DAY;
            dateList.addAll(DateUtils.getBetweenDay(startDate, endDate, DEFAULT_DATE_FORMAT));
        } else if (day > MAX_MONTH_DAY && day <= MAX_3_MONTH_DAY) {
            dateType = WEEK;
            dateList.addAll(DateUtils.getBetweenWeek(startDate, endDate, DEFAULT_WEEK_FORMAT));
        } else if (day > MAX_3_MONTH_DAY && day <= MAX_YEAR_DAY) {
            dateType = MONTH;
            dateList.addAll(DateUtils.getBetweenMonth(startDate, endDate, DEFAULT_MONTH_FORMAT));
        } else {
            throw new RuntimeException("日期参数只能介于0-365天之间");
        }
        return dateType;
    }

    public static String calculationEn(LocalDate startDate, LocalDate endDate, List<String> dateList) {
        if (startDate == null) {
            startDate = LocalDate.now();
        }
        if (endDate == null) {
            endDate = LocalDate.now().plusDays(30);
        }
        if (dateList == null) {
            dateList = new ArrayList<>();
        }
        long day = until(startDate, endDate);

        String dateType = MONTH;
        if (day >= 0 && day <= MAX_MONTH_DAY) {
            dateType = DAY;
            dateList.addAll(DateUtils.getBetweenDay(startDate, endDate, DEFAULT_DATE_FORMAT_EN));
        } else if (day > MAX_MONTH_DAY && day <= MAX_3_MONTH_DAY) {
            dateType = WEEK;
            dateList.addAll(DateUtils.getBetweenWeek(startDate, endDate, DEFAULT_WEEK_FORMAT_EN));
        } else if (day > MAX_3_MONTH_DAY && day <= MAX_YEAR_DAY) {
            dateType = MONTH;
            dateList.addAll(DateUtils.getBetweenMonth(startDate, endDate, DEFAULT_MONTH_FORMAT_EN));
        } else {
            throw new RuntimeException("日期参数只能介于0-365天之间");
        }
        return dateType;
    }

    public static String getDuration(Long duration) {
        if (duration != null){
            long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
            long nh = 1000 * 60 * 60;//一小时的毫秒数
            long hm = 1000 * 60;//一分钟的毫秒数
            long sm = 1000;
            //获得两个时间的毫秒时间差异
            long day = duration / nd;//计算差多少天
            long hour = duration % nd / nh;//计算差多少小时
            long minute = duration % nh / hm;
            long second = duration % hm / sm;
            StringBuffer durationStr = new StringBuffer("");
            if (day > 0){
                durationStr.append(day).append("天 ");
            }
            if (hour > 0){
                durationStr.append(hour).append("时 ");
            }
            if (minute > 0){
                durationStr.append(minute).append("分 ");
            }
            if (second > 0){
                durationStr.append(second).append("秒");
            }
            return durationStr.toString();
        } else {
            return "";
        }
    }

    public static Date getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.atZone(ZONE).toInstant());
    }


    /**
     * 获取昨日时间
     *
     * @param format
     * @return
     */
    public static String getYesterday(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate nowDate = LocalDate.now();
        LocalDate yesterday = nowDate.minusDays(1);
        return yesterday.format(dateTimeFormatter);

    }

    /**
     * 获取上周的时间
     *
     * @param format
     * @return
     */
    public static String getLastWeek(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate nowDate = LocalDate.now();
        LocalDate lastWeek = nowDate.minusWeeks(1);
        return lastWeek.format(dateTimeFormatter);
    }

    /**
     * 获取上个月的时间
     *
     * @param format
     * @return
     */
    public static String getLastMonth(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate nowDate = LocalDate.now();
        LocalDate lastMonth = nowDate.minusMonths(1);
        return lastMonth.format(dateTimeFormatter);

    }

    /**
     * 获取去年的时间
     *
     * @param format
     * @return
     */
    public static String getLastYear(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate nowDate = LocalDate.now();
        LocalDate lastYear = nowDate.minusYears(1);
        return lastYear.format(dateTimeFormatter);
    }

    /**
     * 获取前多少天的日期
     *
     * @param format
     * @param num
     * @return
     */
    public static String getBeforeSomeDay(String format, int num) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate nowDate = LocalDate.now();
        LocalDate beforeDay = nowDate.minusDays(num);
        return beforeDay.format(dateTimeFormatter);
    }

    /**
     * 获取指定时间的前多少天
     *
     * @param format
     * @param date
     * @param num
     * @return
     */
    public static String getBeforeDayOfDate(String format, String date, int num) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        LocalDate beforeDay = localDate.minusDays(num);
        return beforeDay.format(dateTimeFormatter);

    }

    /**
     * 获取当天的开始时间  yyyy-MM-dd 00:00:00
     *
     * @param format
     * @return
     */
    public static String getDayStartTime(String format, String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        LocalDateTime toDayStart = LocalDateTime.of(localDate, LocalTime.MIN);
        return toDayStart.format(FORMATTER);
    }

    public static Date getDayStartTime(Date date) {
        LocalDate localDate = date2LocalDate(date);
        LocalDateTime toDayStart = LocalDateTime.of(localDate, LocalTime.MIN);
        return localDateTime2Date(toDayStart);
    }

    /**
     * 获取当天的结束时间 yyyy-MM-dd 23:59:59
     *
     * @param format
     * @return
     */
    public static String getDayEndTime(String format, String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        LocalDateTime toDayStart = LocalDateTime.of(localDate, LocalTime.MAX);
        return toDayStart.format(FORMATTER);
    }

    /**
     * 获取两个时间之间的间隔天数
     *
     * @param startDate yyyyMMdd
     * @param endDate   yyyyMMdd
     * @return
     */
    public static long getRangeCountOfDate(String startDate, String endDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DS);
        LocalDate startLocalDate = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, dateTimeFormatter);
        long count = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
        return count;

    }

    public static long getRangeCountOfDate(Date startDate, Date endDate) {
        LocalDate startLocalDate = date2LocalDate(startDate);
        LocalDate endLocalDate = date2LocalDate(endDate);
        long count = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
        return count;

    }

    /**
     * 后期两个时间之间的所有日期 【包含开始时间和结束时间】
     *
     * @param startDate yyyyMMdd
     * @param endDate   yyyyMMdd
     * @return
     */
    public static List<String> getRangeOfDate(String startDate, String endDate) {
        List<String> range = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DS);
        LocalDate startLocalDate = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, dateTimeFormatter);
        long count = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
        if (count < 0) {
            return range;
        }

        range = Stream.iterate(startLocalDate, d -> d.plusDays(1)).limit(count + 1).map(
                s -> s.format(dateTimeFormatter)).collect(Collectors.toList());
        return range;
    }

    public static String date2Str(Date date,String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);//注意月份是MM
        return simpleDateFormat.format(date);
    }

    /**
     * 为Date增加分钟,减传负数
     *
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
     * @return 新的日期
     */
    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZONE);
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return Date.from(newDateTime.atZone(ZONE).toInstant());
    }

//----------//----------//----------//----------//----------//----------//----------//----------//----------//----------//----------

    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return ONE_SHORT_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }else{
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }
}
