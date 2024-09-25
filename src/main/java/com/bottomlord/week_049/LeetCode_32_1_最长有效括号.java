package com.bottomlord.week_049;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/6/12 8:25
 */
public class LeetCode_32_1_最长有效括号 {
    public int longestValidParentheses(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j < s.length(); j++) {
                if (isValid(s.substring(i, j))) {
                    ans = Math.max(ans, j - i);
                }
            }
        }

        return ans;
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (!stack.isEmpty() && stack.peek() == ')') {
                stack.pop();
            } else {
                return false;
            }
        }

        return true;
    }
}
