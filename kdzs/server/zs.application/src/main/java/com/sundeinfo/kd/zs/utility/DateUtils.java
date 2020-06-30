package com.sundeinfo.kd.zs.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static int getWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getWeekNameOfDate(Date date) {
        return getWeekNameOfDate(getWeekOfDate(date));
    }

    public static String getWeekNameOfDate(int weekIndex) {
        String[] weekDays = {"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        if (weekIndex < 0) weekIndex = 0;
        return weekDays[weekIndex];
    }

    public static int getMonthOfDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        return Integer.parseInt(simpleDateFormat.format(date));
    }

    public static int getYearOfDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");
        return Integer.parseInt(simpleDateFormat.format(date));
    }

    public static int getSeasonOfDate(Date date) {
        int month = getMonthOfDate(date);
        if (month <= 3) {
            return 1;
        } else if (month <= 6) {
            return 2;
        } else if (month <= 9) {
            return 3;
        } else {
            return 4;
        }
    }

    public static String getSeasonNameOfDate(Date date) {
        return getSeasonNameOfDate(getSeasonOfDate(date));
    }

    public static String getSeasonNameOfDate(int seasonIndex) {
        String[] season = {"", "第一季", "第二季", "第三季", "第四季"};
        return season[seasonIndex];
    }

    public static String dateToStringYMDHM(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public static String dateToStringYMD(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String dateToStringYMDNum(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

    public static Date stringYMDNumToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
