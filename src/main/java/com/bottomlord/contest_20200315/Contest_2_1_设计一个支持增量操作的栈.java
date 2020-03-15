package com.bottomlord.contest_20200315;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/3/15 15:53
 */
public class Contest_2_1_设计一个支持增量操作的栈 {
    class CustomStack {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;
        private int maxSize;
        private int size;

        public CustomStack(int maxSize) {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
            this.maxSize = maxSize;
            this.size = 0;
        }

        public void push(int x) {
            if (size < maxSize) {
                size++;
                stack1.push(x);
            }
        }

        public int pop() {
            if (size == 0) {
                return -1;
            }

            size--;
            return stack1.pop();
        }

        public void increment(int k, int val) {
            if (size == 0) {
                return;
            }

            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

            int num = Math.min(size, k);
            while (!stack2.isEmpty()) {
                if (num-- > 0) {
                    stack1.push(stack2.pop() + val);
                } else {
                    stack1.push(stack2.pop());
                }
            }
        }
    }
}
