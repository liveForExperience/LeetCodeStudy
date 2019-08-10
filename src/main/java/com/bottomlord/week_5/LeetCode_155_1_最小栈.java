package com.bottomlord.week_5;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class LeetCode_155_1_最小栈 {
    class MinStack {
        private Stack<Integer> stack;
        private Queue<Integer> queue;
        public MinStack() {
            this.stack = new Stack<>();
            this.queue = new ArrayDeque<>();
        }

        public void push(int x) {
            this.queue.offer(x);
            if (x < this.queue.peek()) {
                while (this.queue.peek() != x) {
                    this.queue.offer(this.queue.poll());
                }
            }

            this.stack.push(x);
        }

        public void pop() {
            if (!stack.isEmpty()) {
                int num = this.stack.pop();
                int min = queue.peek();
                while (queue.peek() != num) {
                    this.queue.offer(this.queue.poll());
                }
                this.queue.poll();

                if (queue.isEmpty()) {
                    return;
                }

                while (queue.peek() != min) {
                    this.queue.offer(this.queue.poll());
                }
            }
        }

        public int top() {
            return this.stack.peek();
        }

        public int getMin() {
            return this.queue.peek();
        }
    }
}
