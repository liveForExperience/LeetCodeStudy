package com.bottomlord.week_197;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-04-22 22:02:46
 */
public class LeetCode_1027_1_最长等差数列 {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        Map[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                dp[i].put(diff, ((Integer)dp[j].getOrDefault(diff, 0)) + 1);
            }
        }

        int max = 0;
        for (Object obj : dp[n - 1].entrySet()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) obj;
            max = Math.max(max, entry.getValue());
        }

        return max;
    }
}
