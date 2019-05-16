package com.twsihan.extras.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtil
{
    private static final int DEF_DIV_SCALE = 10;

    static DecimalFormat decimalFormat = new DecimalFormat("0.00");


    public static String fromUsage(long free, long total)
    {
        Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(d);
    }

    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static String sub(String value1, String value2)
    {
        value1 = StringUtil.nullTo0(value1);
        value2 = StringUtil.nullTo0(value2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return decimalFormat.format(b1.subtract(b2));
    }

    public static double sum(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static String sum(String v1, String v2)
    {
        v1 = StringUtil.nullTo0(v1);
        v2 = StringUtil.nullTo0(v2);
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return decimalFormat.format(b1.add(b2));
    }

    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static String mul(String value1, String value2)
    {
        value1 = StringUtil.nullTo0(value1);
        value2 = StringUtil.nullTo0(value2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return decimalFormat.format(b1.multiply(b2));
    }

    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static String div(String value1, String value2)
    {
        value1 = StringUtil.nullTo0(value1);
        value2 = StringUtil.nullTo0(value2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return decimalFormat.format(b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN));
    }

    public static double div(double v1, double v2, int scale)
    {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String divide(String value1, String value2)
    {
        value1 = StringUtil.nullTo0(value1);
        value2 = StringUtil.nullTo0(value2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return String.valueOf(b1.divide(b2, 0, BigDecimal.ROUND_FLOOR));
    }

    public static int compare(String value1, String value2)
    {
        value1 = StringUtil.nullTo0(value1);
        value2 = StringUtil.nullTo0(value2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.compareTo(b2);
    }

    public static Double formatDouble(Double b)
    {
        if (b == null) {
            b = 0d;
        }
        BigDecimal bg = new BigDecimal(b);
        return Double.parseDouble(bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }

    public static Double formatDouble(Double b, int position)
    {
        if (b == null) {
            b = 0d;
        }
        BigDecimal bg = new BigDecimal(b);
        return Double.parseDouble(bg.setScale(position, BigDecimal.ROUND_HALF_UP).toString());
    }
}
