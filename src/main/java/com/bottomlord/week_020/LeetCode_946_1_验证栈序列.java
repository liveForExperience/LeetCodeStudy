package com.bottomlord.week_020;

import java.util.Stack;

public class LeetCode_946_1_验证栈序列 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int pop = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[pop]) {
                stack.pop();
                pop++;
            }
        }

        return pop == popped.length;
    }
}
