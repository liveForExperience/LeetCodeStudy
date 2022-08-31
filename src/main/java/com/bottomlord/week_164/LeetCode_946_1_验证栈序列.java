package com.bottomlord.week_164;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-08-31 22:10:52
 */
public class LeetCode_946_1_验证栈序列 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int pui = 0, poi = 0, pulen = pushed.length, polen = popped.length;
        while (pui < pulen) {
            while (poi < polen && !stack.isEmpty() && stack.peek() == popped[poi]) {
                stack.pop();
                poi++;
            }

            stack.push(pushed[pui++]);
        }

        while (poi < polen && !stack.isEmpty() && stack.peek() == popped[poi]) {
            stack.pop();
            poi++;
        }

        return stack.isEmpty();
    }
}
