package com.bottomlord.week_049;

/**
 * @author ChenYue
 * @date 2020/6/8 12:16
 */
public class LeetCode_29_2 {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int result = 0;
        boolean positive = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0;

        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);

        while (dividend <= divisor) {
            int num = divisor;
            int count = 1;
            while (dividend - num <= num) {
                num <<= 1;
                count <<= 1;
            }

            dividend -= num;
            result += count;
        }

        return positive ? result : -result;
    }
}