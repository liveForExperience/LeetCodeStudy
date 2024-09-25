package com.bottomlord.week_034;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/2/24 9:15
 */
public class Interview_30_1_包含min函数的栈 {
    class MinStack {
        private Stack<Integer> stack;
        private int min;
        public MinStack() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }

            stack.push(x);
        }

        public void pop() {
            if (stack.pop() == min) {
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min;
        }
    }
}
