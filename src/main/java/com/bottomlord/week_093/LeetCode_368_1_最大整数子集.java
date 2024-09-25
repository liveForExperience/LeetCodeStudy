package com.bottomlord.week_093;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeetCode_368_1_最大整数子集 {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;

        if (len == 0) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List[] dp = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new ArrayList();
        }
        dp[0].add(nums[0]);

        List<Integer> ans = dp[0];

        for (int i = 1; i < len; i++) {
            dp[i].add(nums[i]);
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i].size() <= dp[j].size()) {
                    dp[i] = new ArrayList(dp[j]);
                    dp[i].add(nums[i]);
                }

                if (dp[i].size() > ans.size()) {
                    ans = dp[i];
                }
            }
        }

        return ans;
    }
}
