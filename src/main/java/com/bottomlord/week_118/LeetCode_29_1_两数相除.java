package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-12 08:31:51
 */
public class LeetCode_29_1_两数相除 {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean negative = dividend < 0 ^ divisor < 0;
        long d = dividend, r = divisor;
        d = Math.abs(d);
        r = Math.abs(r);

        int result = 0;
        while (d >= r) {
            long num = r, count = 1;

            while (d - num >= num) {
                num <<= 1;
                count <<= 1;
            }

            d -= num;
            result += count;
        }

        return negative ? -result : result;
    }
}
