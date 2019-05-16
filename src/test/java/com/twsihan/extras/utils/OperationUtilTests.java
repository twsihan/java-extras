package com.twsihan.extras.utils;

import org.junit.jupiter.api.Test;

public class OperationUtilTests
{


    @Test
    public void test()
    {
        double gain = 0.001d;
        double free_num = 0.120d;
        System.out.println(Double.sum(gain, free_num));
        System.out.println(MathUtil.sum("0.001", "1.120"));
        System.out.println(MathUtil.compare("2", "0"));
        System.out.println(MathUtil.compare("-2", "0"));
        System.out.println(MathUtil.compare("0", "0"));
    }
}
