package com.bottomlord.week_022;

import java.util.Stack;

public class LeetCode_503_2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = (n - 1) * 2; i >= 0; i--) {
            while (!stack.empty() && nums[i % n] >= nums[stack.peek()]) {
                stack.pop();
            }

            ans[i % n] = stack.empty() ? -1 : nums[stack.peek()];

            stack.push(i % n);
        }

        return ans;
    }
}