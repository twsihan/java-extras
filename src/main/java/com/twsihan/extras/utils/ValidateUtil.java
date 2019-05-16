package com.twsihan.extras.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil
{


    public static Boolean required(String value)
    {
        Boolean result = true;
        if (value == null || "".equals(value.trim())) {
            result = false;
        }
        return result;
    }

    public static boolean empty(String value)
    {
        if (null == value || "".equals(value) || "".equals(value.trim()) || "null".equalsIgnoreCase(value)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean notEmpty(String value)
    {
        if (null == value || "".equals(value) || "".equals(value.trim()) || "null".equalsIgnoreCase(value)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean numeric(String value)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(value).matches();
    }

    public static boolean chinese(String value)
    {
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5]+$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    public static boolean number(String value)
    {
        Pattern p = Pattern.compile("^\\+?[1-9][0-9]*$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    public static boolean money(String value)
    {
        Pattern p = Pattern.compile("^\\d+(\\.\\d\\d?)?$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    // 匹配中文 数字 字母 下划线
    public static boolean input(String value)
    {
        Pattern p = Pattern.compile("^[\\w\\u4e00-\\u9fa5]+$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    // 手机号验证
    public static boolean mobile(String mobiles)
    {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    // 身份证
    public static boolean idcard(String value)
    {
        Pattern p = Pattern.compile("^(\\d{14}|\\d{17})(\\d|[xX])$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    // 邮箱
    public static boolean email(String value)
    {
        Pattern p = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    // 传值或固定电话
    public static boolean phone(String value)
    {
        Pattern p = Pattern.compile("^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    // 邮政编码
    public static boolean postNO(String value)
    {
        Pattern p = Pattern.compile("^\\d{6}$");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
