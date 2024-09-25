package com.bottomlord.week_037;

import java.util.Objects;
import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/3/20 12:47
 */
public class Interview_0302_1_栈的最小值 {
    class MinStack {
        private Stack<Integer> data;
        private Stack<Integer> stack;
        public MinStack() {
            this.data = new Stack<>();
            this.stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty() || stack.peek() >= x) {
                stack.push(x);
            }

            data.push(x);
        }

        public void pop() {
            if (data.isEmpty()) {
                return;
            }

            if (!stack.isEmpty() && Objects.equals(data.peek(), stack.peek())) {
                stack.pop();
            }

            data.pop();
        }

        public int top() {
            return data.isEmpty() ? -1 : data.peek();
        }

        public int getMin() {
            return stack.isEmpty() ? -1 : stack.peek();
        }
    }
}
