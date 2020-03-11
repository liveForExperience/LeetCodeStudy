package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/11 12:28
 */
public class Interview_65_1_不用加减乘除做加法 {
    public int add(int a, int b) {
        while (b != 0) {
            int plus = a ^ b;
            b = (a & b) << 1;
            a = plus;
        }
        return a;
    }
}
