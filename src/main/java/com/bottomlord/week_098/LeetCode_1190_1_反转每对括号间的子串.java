package com.bottomlord.week_098;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2021/5/26 8:17
 */
public class LeetCode_1190_1_反转每对括号间的子串 {
    public String reverseParentheses(String s) {
        char[] cs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c != ')') {
                stack.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    char pushedC = stack.pop();
                    if (pushedC != '(') {
                        sb.append(pushedC);
                    } else {
                        break;
                    }
                }

                sb.reverse();
                for (int j = 0; j < sb.length(); j++) {
                    stack.push(sb.charAt(j));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
