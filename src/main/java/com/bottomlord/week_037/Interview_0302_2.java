package com.bottomlord.week_037;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/3/20 13:04
 */
public class Interview_0302_2 {
    class MinStack {
        private int min;
        private Stack<Integer> stack;
        public MinStack() {
            this.min = Integer.MAX_VALUE;
            this.stack = new Stack<>();
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

        public int getMin() {
            return min;
        }
    }
}
