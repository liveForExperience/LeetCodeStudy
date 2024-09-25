package com.bottomlord.week_049;

/**
 * @author ChenYue
 * @date 2020/6/8 8:43
 */
public class LeetCode_29_1_两数相除 {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean positive = false;
        if ((dividend > 0 && divisor > 0) ||
            (dividend < 0 && divisor < 0)) {
            positive = true;
        }

        int result = 0;

        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);

        while (dividend <= divisor) {
            dividend -= divisor;
            result++;
        }

        return positive ? result : -result;
    }
}
