package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-25 14:43:52
 */
public class LeetCode_1716_1_计算力扣银行的钱 {
    public int totalMoney(int n) {
        int start = 1, day = 1, sum = 0,
            week = n / 7, left = n % 7;

        for (int i = 0; i < week; i++) {
            sum += start++ * 7 + 21;
        }

        return sum + left * (left - 1) / 2 + start * left;
    }
}
