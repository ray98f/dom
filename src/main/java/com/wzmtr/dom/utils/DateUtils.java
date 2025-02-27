package com.wzmtr.dom.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.wzmtr.dom.dto.res.common.DateResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String[] PARSE_PATTERNS = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    /**
     * 返回当前时间的格式为YYYY_MM_DD_HH_MM_SS
     * @return 当前时间
     */
    public static String getCurrentTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }


    public static Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 返回当前时间的格式为YYYYMMDD
     * @return 当前时间
     */
    public static String getNoDate() {
        return dateTimeNow(YYYYMMDD);
    }

    public static String getNoCurrentTime() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static Date currentDate() {
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 将字符串解析为Date对象
        try {
            String format = dateFormatter.format(now);
            // 输出Date对象
            return dateFormatter.parse(format);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date formatDateYYYY_MM_DD_HH_MM_SS(Date date) {
        // 创建一个新的Calendar实例
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 设置默认时间为00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // 使用新的Calendar实例创建一个新的Date对象
        return calendar.getTime();
    }

    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str, PARSE_PATTERNS);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 比较两个相同格式的字符串时间
     * @param firstDate 第一个时间
     * @param secondDate 第二个时间
     * @param pattern 时间格式
     * @return 时间大小状态
     */
    public static Integer dateCompare(String firstDate, String secondDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date d1 = sdf.parse(firstDate);
            Date d2 = sdf.parse(secondDate);
            return Long.compare(d1.getTime(), d2.getTime());
        } catch (Exception e) {
            log.error("compare err", e);
            return -99;
        }
    }

    /**
     * 获取当前时间加减月份后的日期
     * @return 日期
     */
    public static String getDayByMonth(int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, monthNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 校验字符串是否为对应的日期格式
     * @return boolean
     */
    public static boolean isValidDateFormat(String dateStr, String dateFormat) {
        if (ObjectUtil.isEmpty(dateStr)) {
            return false;
        }
        try {
            // 将字符串解析为日期对象，如果解析成功，则说明字符串是有效的日期格式；否则说明字符串不是有效的日期格式。
            DateUtil.parse(dateStr, dateFormat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据日期获取上周日期范围
     * @param day 日期（yyyy-MM-dd）
     * @return 上周日期范围
     */
    public static DateResDTO getLastWeek(String day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取上一周
        calendar.add(Calendar.DATE, -7);
        // 设置为周一
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date monday = calendar.getTime();
        // 设置为周日
        calendar.add(Calendar.DATE, 6);
        Date sunday = calendar.getTime();
        DateResDTO dto = new DateResDTO();
        dto.setStartDate(sdf.format(monday));
        dto.setEndDate(sdf.format(sunday));
        return dto;
    }

    /**
     * 根据日期获取上月日期范围
     * @param day 日期（yyyy-MM-dd）
     * @return 上月日期范围
     */
    public static DateResDTO getLastMonth(String day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //上个月第一天
        Date startTime = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //上个月最后一天
        Date endTime = calendar.getTime();
        DateResDTO dto = new DateResDTO();
        dto.setStartDate(sdf.format(startTime));
        dto.setEndDate(sdf.format(endTime));
        return dto;
    }

    /**
     * 获取两个日期相差天数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 相差天数
     * @throws ParseException 异常
     */
    public static Long getDayBetween(String startDate, String endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(startDate);
        Date date2 = dateFormat.parse(endDate);
        long diff = date2.getTime() - date1.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }

}
