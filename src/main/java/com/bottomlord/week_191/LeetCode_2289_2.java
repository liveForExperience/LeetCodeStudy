package com.bottomlord.week_191;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2023-03-09 23:25:23
 */
public class LeetCode_2289_2 {
    public int totalSteps(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                int index = stack.pop();
                cur = Math.max(dp[index], cur);
            }

            cur = stack.isEmpty() ? 0 : cur + 1;
            dp[i] = Math.max(dp[i], cur);
            ans = Math.max(ans, dp[i]);
            stack.push(i);
        }

        return ans;
    }
}