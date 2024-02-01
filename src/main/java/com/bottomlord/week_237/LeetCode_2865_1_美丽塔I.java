package com.bottomlord.week_237;

import java.util.List;

/**
 * @author chen yue
 * @date 2024-01-25 18:33:18
 */
public class LeetCode_2865_1_美丽塔I {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long max = 0, n = maxHeights.size();
        for (int i = 0; i < maxHeights.size(); i++) {
            long num = maxHeights.get(i), pre = num, sum = num;

            for (int j = i + 1; j < n; j++) {
                pre = Math.min(pre, maxHeights.get(j));
                sum += pre;
            }

            pre = num;
            for (int j = i - 1; j >= 0; j--) {
                pre = Math.min(pre, maxHeights.get(j));
                sum += pre;
            }

            max = Math.max(sum, max);
        }

        return max;
    }
}