package com.bottomlord.week_199;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2023-05-03 11:36:05
 */
public class LeetCode_1003_2 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == 'c') {
                if (stack.size() < 2) {
                    return false;
                }

                if (stack.pop() != 'b') {
                    return false;
                }

                if (stack.pop() != 'a') {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}