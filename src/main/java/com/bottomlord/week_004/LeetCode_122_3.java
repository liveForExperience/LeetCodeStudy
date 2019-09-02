package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/8/1 14:08
 */
public class LeetCode_122_3 {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }
}
