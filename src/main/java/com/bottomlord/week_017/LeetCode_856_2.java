package com.bottomlord.week_017;

import java.util.Stack;

public class LeetCode_856_2 {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        char[] cs = S.toCharArray();
        for (char c : cs) {
            if (c == '(') {
                stack.push(0);
            } else {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(second + Math.max(first * 2, 1));
            }
        }
        return stack.pop();
    }
}