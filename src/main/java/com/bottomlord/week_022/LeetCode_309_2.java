package com.bottomlord.week_022;

public class LeetCode_309_2 {
    public int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE, rest = 0, pre = 0;
        for (int price : prices) {
            int tmp = rest;
            rest = Math.max(rest, hold + price);
            hold = Math.max(hold, pre - price);
            pre = tmp;
        }

        return rest;
    }
}
