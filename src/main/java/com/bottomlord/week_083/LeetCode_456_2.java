package com.bottomlord.week_083;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2021/2/12 19:47
 */
public class LeetCode_456_2 {
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        int[] mins = new int[len];
        mins[0] = nums[0];
        for (int i = 1; i < len; i++) {
            mins[i] = Math.min(nums[i], mins[i - 1]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= mins[i]) {
                stack.pop();
            }

            if (!stack.isEmpty() && mins[i] < stack.peek() && stack.peek() < nums[i]) {
                return true;
            }

            stack.push(nums[i]);
        }

        return false;
    }
}
