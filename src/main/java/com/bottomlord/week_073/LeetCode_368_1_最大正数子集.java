package com.bottomlord.week_073;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/12/2 12:03
 */
public class LeetCode_368_1_最大正数子集 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List[] dp = new List[len];
        dp[0] = new ArrayList();
        dp[0].add(nums[0]);

        List<Integer> ans = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = new ArrayList();
            dp[i].add(nums[i]);
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i].size() < dp[j].size() + 1) {
                    dp[i] = new ArrayList(dp[j]);
                    dp[i].add(nums[i]);
                }
            }

            if (ans.size() < dp[i].size()) {
                ans = dp[i];
            }
        }

        return ans;
    }
}