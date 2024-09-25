package com.bottomlord.week_109;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-08-11 08:20:47
 */
public class LeetCode_446_1_等差数列划分II_子序列 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length, ans = 0;
        Map<Long, Integer>[] dp = new Map[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new HashMap<>();
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long d = (long) nums[i] - nums[j];
                int jCount = dp[j].getOrDefault(d, 0);
                Map<Long, Integer> iMap = dp[i];
                ans += jCount;
                iMap.put(d, iMap.getOrDefault(d, 0) + jCount + 1);
            }
        }

        return ans;
    }
}
