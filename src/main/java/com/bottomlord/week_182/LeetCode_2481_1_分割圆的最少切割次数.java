package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-03 09:21:13
 */
public class LeetCode_2481_1_分割圆的最少切割次数 {
    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }

        return n % 2 == 0 ? n / 2 : n;
    }
}
