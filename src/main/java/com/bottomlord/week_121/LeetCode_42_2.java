package com.bottomlord.week_121;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2021-11-03 23:34:49
 */
public class LeetCode_42_2 {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int ans = 0;
        for (int i = 1; i < height.length; i++) {
            while (height[i] > height[stack.peek()]) {
                int top = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }

                int dis = i - stack.peek() - 1,
                    h = Math.min(height[i], height[stack.peek()]) - height[top];

                ans += dis * h;
            }

            stack.push(i);
        }

        return ans;
    }
}
