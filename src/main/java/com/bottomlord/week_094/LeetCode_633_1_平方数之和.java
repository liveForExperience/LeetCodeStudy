package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/28 8:38
 */
public class LeetCode_633_1_平方数之和 {
    public boolean judgeSquareSum(int c) {
        for (int a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - (a * a));
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }
}
