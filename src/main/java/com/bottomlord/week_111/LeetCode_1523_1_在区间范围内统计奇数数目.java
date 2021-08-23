package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-23 08:37:23
 */
public class LeetCode_1523_1_在区间范围内统计奇数数目 {
    public int countOdds(int low, int high) {
        int odd = 0;
        for (int i = low; i <= high; i++) {
            odd += i % 2 == 1 ? 1 : 0;
        }
        return odd;
    }
}
