package com.bottomlord.week_005;

import java.util.Stack;

public class LeetCode_155_2 {
    class MinStack {
        private Stack<Long> stack;
        private Long min;
        public MinStack() {
            this.stack = new Stack<>();
        }

        public void push(int x) {
            if (this.stack.isEmpty()) {
                this.stack.push(0L);
                this.min = (long)x;
            } else {
                this.stack.push(x - min);
                if (x < min) {
                    this.min = (long)x;
                }
            }
        }

        public void pop() {
            if (this.stack.isEmpty()) {
                return;
            }

            long diff = this.stack.pop();
            if (diff < 0) {
                this.min -= diff;
            }
        }

        public int top() {
            Long diff = this.stack.peek();
            if (diff < 0) {
                return Math.toIntExact(min);
            }
            return Math.toIntExact(min + diff);
        }

        public int getMin() {
            return Math.toIntExact(this.min);
        }
    }
}
