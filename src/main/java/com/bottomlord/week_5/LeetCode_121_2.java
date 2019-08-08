package com.bottomlord.week_5;

public class LeetCode_121_2 {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                profit = Math.max(profit, price - min);
            }
        }
        return profit;
    }
}
