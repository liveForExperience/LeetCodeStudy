package com.bottomlord.week_034;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/2/24 11:15
 */
public class Interview_31_1_栈的压入和弹出序列 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int push = 0, pop = 0;
        while (push < pushed.length || pop < popped.length) {
            if (!stack.isEmpty() && stack.peek() == popped[pop]) {
                stack.pop();
                pop++;
                continue;
            }

            if (push == pushed.length) {
                break;
            }

            stack.push(pushed[push++]);
        }

        return stack.isEmpty();
    }
}
