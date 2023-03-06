package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-03-06 16:19:03
 */
public class LeetCode_1599_1_经营摩天轮的最大利润 {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int left = 0, n = customers.length;
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] > 4) {
                left = customers[i] - 4;
                customers[i] = 4;
            }

            if (i < customers.length - 1) {
                customers[i + 1] += left;
                left = 0;
            }
        }

        int add = left / 4 + left % 4 == 0 ? 0 : 1;
        int[] cs = new int[n + add];
        System.arraycopy(customers, 0, cs, 0, n);
        for (int i = n; i < n + add; i++) {
            if (left > 4) {
                cs[i] = 4;
                left -= 4;
            } else {
                cs[i] = left;
            }
        }

        int profit = 0, max = Integer.MIN_VALUE, ans = 0;
        int[] profits = new int[cs.length];
        for (int i = 0; i < profits.length; i++) {
            profits[i] = cs[i] * boardingCost - runningCost;
            profit += profits[i];

            if (profit > max) {
                ans = i;
                max = profit;
            }
        }

        return max > 0 ? ans + 1 : -1;
    }
}
