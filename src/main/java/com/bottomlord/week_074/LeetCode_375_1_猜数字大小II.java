package com.bottomlord.week_074;

/**
 * @author ChenYue
 * @date 2020/12/9 9:07
 */
public class LeetCode_375_1_猜数字大小II {
    public int getMoneyAmount(int n) {
        return recurse(1, n);
    }

    private int recurse(int start, int end) {
        if (start >= end) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int sum = i + Math.max(recurse(start, i - 1), recurse(i + 1, end));
            min = Math.min(min, sum);
        }

        return min;
    }
}
