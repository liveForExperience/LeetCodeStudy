package com.bottomlord.week_059;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/8/23 13:29
 */
public class LeetCode_224_1_基本计算器 {
    public int calculate(String s) {
        int operand = 0, n = 0;
        Stack<Object> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                operand = (int) Math.pow(10, n) * (int)(c - '0') + operand;
                n++;
            } else {
                if (n != 0) {
                    stack.push(operand);
                    n = 0;
                    operand = 0;
                }

                if (c == '(') {
                    int result = doCal(stack);
                    stack.pop();
                    stack.push(result);
                } else {
                    stack.push(c);
                }
            }
        }

        if (n != 0) {
            stack.push(operand);
        }

        return doCal(stack);
    }

    private int doCal(Stack<Object> stack) {
        int result = 0;

        if (!stack.isEmpty()) {
            result = (int)stack.pop();
        }

        while (!stack.isEmpty() && (char)stack.peek() != ')') {
            char c = (char) stack.pop();

            if (c == '+') {
                result += (int) stack.pop();
            } else if (c == '-') {
                result -= (int) stack.pop();
            }
        }

        return result;
    }
}
