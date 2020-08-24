package com.bottomlord.week_060;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/8/24 8:29
 */
public class LeetCode_227_1_基本计算器II {
    public int calculate(String s) {
        s = s.trim();

        int num = 0; char op = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(num * stack.pop());
                } else {
                    stack.push(stack.pop() / num);
                }

                op = s.charAt(i);
                num = 0;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}
