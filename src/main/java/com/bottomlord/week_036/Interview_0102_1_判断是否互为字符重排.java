package com.bottomlord.week_036;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/14 14:07
 */
public class Interview_0102_1_判断是否互为字符重排 {
    public boolean CheckPermutation(String s1, String s2) {
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);

        return Arrays.equals(cs1, cs2);
    }
}
