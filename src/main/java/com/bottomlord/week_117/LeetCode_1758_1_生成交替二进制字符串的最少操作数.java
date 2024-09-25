package com.bottomlord.week_117;

/**
 * @author chen yue
 * @date 2021-10-04 23:00:30
 */
public class LeetCode_1758_1_生成交替二进制字符串的最少操作数 {
    public int minOperations(String s) {
        char[] cs = s.toCharArray();
        boolean zero = true, one = true;
        int a = 0, b = 0;
        for (char c : cs) {
            if (c == '0') {
                a += zero ? 0 : 1;
                b += one ? 1 : 0;
            } else {
                a += zero ? 1 : 0;
                b += one ? 0 :  1;
            }

            zero = !zero;
            one = !one;
        }

        return Math.min(a, b);
    }
}
