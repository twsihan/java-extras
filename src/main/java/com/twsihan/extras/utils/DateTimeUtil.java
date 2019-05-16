package com.twsihan.extras.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil
{


    public static String now()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String date()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static long timestamp()
    {
        return System.currentTimeMillis();
    }

    public static String getSpecifiedDayBefore(String specifiedDay)
    {
        String dayBefore = "";
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
            calendar.setTime(date);
            int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, day - 1);
            dayBefore = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayBefore;
    }

    public static String getSpecifiedDayAfter(String specifiedDay)
    {
        String dayAfter = "";
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
            calendar.setTime(date);
            int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, day + 1);
            dayAfter = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayAfter;
    }
}
