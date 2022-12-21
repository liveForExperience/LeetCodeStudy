package com.bottomlord.week_179;

/**
 * @author chen yue
 * @date 2022-12-21 09:20:02
 */
public class LeetCode_1750_1_移除石子的最大得分 {
    public int maximumScore(int a, int b, int c) {
        int sum = a + b + c;
        int max = Math.max(a, Math.max(b, c));
        return Math.min(sum - max, sum / 2);
    }
}
