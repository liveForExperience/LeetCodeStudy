package com.bottomlord.week_178;

/**
 * @author chen yue
 * @date 2022-12-09 16:03:03
 */
public class LeetCode_2413_1_最小偶数倍 {
    public int smallestEvenMultiple(int n) {
        if (n <= 2) {
            return 2;
        }

        return n % 2 == 0 ? n : 2 * n;
    }
}
