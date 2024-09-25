package com.bottomlord.week_173;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-11-07 11:23:35
 */
public class LeetCode_1106_1_解析布尔表达式 {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        char[] cs = expression.toCharArray();

        for (char c : cs) {
            if (c == ',') {
            } else if (c != ')') {
                stack.push(c);
            } else {
                int t = 0, f = 0;
                while (stack.peek() != '(') {
                    char token = stack.pop();
                    if (token == 't') {
                        t++;
                    } else {
                        f++;
                    }
                }

                stack.pop();
                char operator = stack.pop();

                if (operator == '!') {
                    stack.push(t > 0 ? 'f' : 't');
                } else if (operator == '|') {
                    stack.push(t > 0 ? 't' : 'f');
                } else {
                    stack.push(f > 0 ? 'f' : 't');
                }
            }
        }

        return stack.pop() == 't';
    }
}
