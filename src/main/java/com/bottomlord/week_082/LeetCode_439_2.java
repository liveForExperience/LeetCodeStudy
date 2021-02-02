package com.bottomlord.week_082;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2021/2/2 8:54
 */
public class LeetCode_439_2 {
    public String parseTernary(String expression) {
        int len = expression.length();
        char[] cs = expression.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = len - 1; i >= 2; i-=2) {
            if (cs[i - 1] == ':') {
                stack.push(cs[i]);
            } else {
                if (cs[i - 2] == 'T') {
                    cs[i - 2] = cs[i];
                } else {
                    cs[i - 2] = stack.peek();
                }
                stack.pop();
            }
        }
        return "" + cs[0];
    }
}
