package com.bottomlord.week_018;

import java.util.Stack;

public class LeetCode_1006_2 {
    public boolean isValid(String S) {
        char[] cs = S.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : cs) {
            if (c != 'c') {
                stack.push(c);
            } else {
                if (stack.size() < 2) {
                    return false;
                } else {
                    char c1 = stack.pop();
                    if (c1 != 'b') {
                        return false;
                    }

                    char c2 = stack.pop();
                    if (c2 != 'a') {
                        return false;
                    }
                }
            }
        }

        return stack.size() == 0;
    }
}