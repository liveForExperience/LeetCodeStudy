package com.bottomlord.week_015;

public class LeetCode_714_1_买卖股票的最佳时机含手续费 {
    public int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}
