package com.bottomlord.week_027;

import java.util.Objects;
import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/1/8 21:28
 */
public class LeetCode_150_1_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            int num;
            if (Objects.equals(token, "+")) {
                num = stack.pop() + stack.pop();
            } else if (Objects.equals(token, "-")) {
                Integer second = stack.pop();
                Integer first = stack.pop();
                num = first - second;
            } else if (Objects.equals(token, "*")) {
                num = stack.pop() * stack.pop();
            } else if (Objects.equals(token, "/")) {
                Integer second = stack.pop();
                Integer first = stack.pop();
                num = first / second;
            } else {
                num = Integer.parseInt(token);
            }

            stack.push(num);
        }

        return stack.pop();
    }
}
