package com.twsihan.extras.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{


    public static int stringToInt(String str)
    {
        int num = 0;
        if (str == null || str.equals("")) {
            return num;
        }
        return Integer.parseInt(str);
    }

    public static double stringToDouble(String str)
    {
        if (str != null && !str.equals("")) {
            return Double.valueOf(str);
        }
        return 0d;
    }

    public static String replaceBlank(String str)
    {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String nextState(String state)
    {
        int m = Integer.parseInt(state) + 1;
        if (m < 10) {
            state = "0" + m;
        } else {
            state = String.valueOf(m);
        }
        return state;
    }

    public static String nullTo0(String val)
    {
        if (val == null || val.equals("")) {
            val = "0";
        }
        return val;
    }
}
