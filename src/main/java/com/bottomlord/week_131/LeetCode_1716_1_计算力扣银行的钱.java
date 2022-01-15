package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-15 11:12:33
 */
public class LeetCode_1716_1_计算力扣银行的钱 {
    public int totalMoney(int n) {
        int week = n / 7, left = n % 7, start = 1, sum = 0;
        for (int i = 0; i < week; i++) {
            sum += start++ * 7 + 21;
        }

        return sum + start * left + left * (left - 1) / 2;
    }
}
