package com.bottomlord.week_210;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2023-07-23 15:08:15
 */
public class LeetCode_42_1_接雨水 {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ans = 0;

        for (int i = 1; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int b = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }

                while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                    int a = stack.pop(), c = i;
                    ans += (height[a] - height[b]) * (c - a + 1);
                }
            }

            stack.push(i);
        }

        return ans;
    }
}
