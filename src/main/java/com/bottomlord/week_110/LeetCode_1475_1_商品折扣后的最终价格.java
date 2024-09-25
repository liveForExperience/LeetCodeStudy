package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-17 08:19:56
 */
public class LeetCode_1475_1_商品折扣后的最终价格 {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int price = prices[i];
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= price) {
                    price -= prices[j];
                    break;
                }
            }

            ans[i] = price;
        }

        return ans;
    }
}
