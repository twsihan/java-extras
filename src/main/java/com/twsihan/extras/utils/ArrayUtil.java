package com.twsihan.extras.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ArrayUtil
{


    public final static Integer compareMax(HashMap<Integer, Integer> map)
    {
        Iterator<Entry<Integer, Integer>> iter = map.entrySet().iterator();
        Integer key = null;
        Integer value = null;
        while (iter.hasNext()) {
            Entry<Integer, Integer> entry = iter.next();
            if (key != null && value != null) {
                if (value < entry.getValue()) {
                    key  = entry.getKey();
                    value = entry.getValue();
                }
            } else {
                value = entry.getValue();
                key = (value > 0) ? entry.getKey() : 0;
            }
        }
        return key;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] instance(Class<T> cls, int length)
    {
        return (T[]) java.lang.reflect.Array.newInstance(cls, length);
    }

    public static int sum(int FromNumber, int ToNumber, int... step)
    {
        int s = (step.length == 0) ? 1 : step[0];
        int k = 0;
        for (int i = Math.min(FromNumber, ToNumber)/** L boundary */
        ; i <= Math.max(FromNumber, ToNumber)/** R boundary */
        ; i += s)
            k += i;
        return k;
    }

    public static int sum(Object[] a)
    {
        int sum = 0;
        for (Object o : a) {
            if (o instanceof Integer)
                sum += (Integer) o;
            else
                continue;
        }
        return sum;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] range(T start, T end, int... step)
    {
        int s = (step.length == 0) ? 1 : step[0];
        int L;
        if (start instanceof Integer & end instanceof Integer) { // 全数字
            L = Math.abs(((Integer) start - (Integer) end - 1)) / s;
            Integer[] rs = instance(Integer.class, L);
            for (int i = 0, v = Math.min((Integer) start, (Integer) end); i < L; i++, v += s) {
                rs[i] = v;
            }
            return (T[]) rs;
        } else if (start instanceof Character ^ end instanceof Character) { // 混合字符数字
            /**
             * 太烦躁,不写了 // 伪代码 顶针 a = 10 -> z = 35 p1 在区间10,35左边
             * P1在[10,35]之间->映射到字符 p1 在区间10,35右边 p1在参数1 p1在参数2 回文 联接结果集 done
             */
        } else if (start instanceof Character & end instanceof Character) {// 全字符
            /** a = 10 b = 11 ... z=35 板拇指数了一下 英文字母的确是26个！今天唯一的收获 */
            int boundry_L = Character.getNumericValue((Character) start);
            int boundry_R = Character.getNumericValue((Character) end);
            boolean need2rev = false;
            // 如果参数1大于参数2 z....a 重写置换区间

            if (boundry_R <= boundry_L) {
                need2rev = true;
                // 如果需要回文显示 如 z-b z,y,x,w,...d,e,b 回调回文方法并打印
                int temp = boundry_L;
                boundry_L = boundry_R;
                boundry_R = temp;
            }
            Character value = Character.forDigit(boundry_L, Character.MAX_RADIX);
            L = (boundry_R - boundry_L + 1) / s;
            Character[] rs = instance(Character.class, L);
            // fill the data array
            for (int i = 0; i < rs.length; i++) {
                rs[i] = value;
                value = Character.forDigit((boundry_L += s), Character.MAX_RADIX);
            }
            if (need2rev)
                reverseString(rs);
            return (T[]) rs;
        } else {
            L = 0;
            System.out.println("不支持的类型");
            System.exit(0);
            throw new RuntimeException();
        }
        return null;
    }

    public static void reverseString(Character[] s)
    {
        int n = s.length - 1;
        for (int j = (n - 1) >> 1; j >= 0; --j) {
            Character temp1 = s[j];
            Character temp2 = s[n - j];
            s[j] = temp2;
            s[n - j] = temp1;
        }
    }

    public static Object php_parse_DateAndString(Object unix) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (unix instanceof Date) { // 得到一个日期对象的字符串格式化表示
            return sdf.format(unix);
        } else if (unix instanceof String) {
            /** 得到一个字符串对应的date对象并返回 */
            Date D = sdf.parse(unix.toString());
            return D;
        } else
            throw new ParseException(null, 0);
    }

    public static List<String> removeSameItem(List<String> list)
    {
        List<String> difList = new ArrayList<String>();
        for (String t : list) {
            if (t != null && !difList.contains(t)) {
                difList.add(t);
            }
        }
        return difList;
    }

    public static <T extends Object> T flushObject(T t, Map<String, Object> params)
    {
        if (params == null || t == null)
            return t;

        Class<?> clazz = t.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();

                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName(); // 获取属性的名字
                    Object value = params.get(name);
                    if (value != null && !"".equals(value)) {
                        // 注意下面这句，不设置true的话，不能修改private类型变量的值
                        fields[i].setAccessible(true);
                        fields[i].set(t, value);
                    }
                }
            } catch (Exception e) {
            }

        }
        return t;
    }
}
