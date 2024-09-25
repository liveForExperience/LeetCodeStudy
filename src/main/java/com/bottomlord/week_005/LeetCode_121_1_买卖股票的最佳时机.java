package com.bottomlord.week_005;

public class LeetCode_121_1_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            int one = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - one);
            }
        }
        return max;
    }
}
