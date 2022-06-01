package com.bottomlord.week_151;

/**
 * @author chen yue
 * @date 2022-06-01 14:34:38
 */
public class LeetCode_633_1_平方数之和 {
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; i++) {
            long l = i, r = c;

            while (l <= r) {
                long mid = (r - l) / 2 + l;
                long num = i * i + mid * mid;

                if (num == c) {
                    return true;
                } else if (num < c) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return false;
    }
}
