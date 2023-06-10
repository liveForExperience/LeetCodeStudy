package com.bottomlord.week_204;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-06-10 10:16:02
 */
public class LeetCode_2517_1_礼盒的最大甜蜜度 {
    public int maximumTastiness(int[] prices, int k) {
        Arrays.sort(prices);
        int tail = prices[prices.length - 1] - prices[0], head = 0;

        while (head <= tail) {
            int mid = (tail - head) / 2 + head;

            if (check(prices, mid, k)) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return head;
    }

    private boolean check(int[] prices, int target, int k) {
        int pre = Integer.MIN_VALUE / 2, count = 0;
        for (int price : prices) {
            if (price - pre >= target) {
                count++;
                pre = price;
            }
        }

        return count >= k;
    }
}
