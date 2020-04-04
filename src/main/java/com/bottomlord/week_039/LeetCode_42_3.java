package com.bottomlord.week_039;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/4/4 18:14
 */
public class LeetCode_42_3 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ans = 0;
        for (int i = 1; i < len; i++) {
            while (height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                int num = Math.min(height[i], height[stack.peek()]) - height[top];
                ans += distance * num;
            }
            stack.push(i);
        }
        return ans;
    }
}