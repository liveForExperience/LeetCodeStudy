package com.bottomlord.weekj_243;

import java.util.LinkedList;
import java.util.function.Supplier;

/**
 * @author chen yue
 * @date 2024-03-05 10:19:57
 */
public class LeetCode_232_1_用栈实现队列 {
    class MyQueue {

        private LinkedList<Integer> in, out;

        public MyQueue() {
            this.in = new LinkedList<>();
            this.out = new LinkedList<>();
        }

        public void push(int x) {
            this.in.push(x);
        }

        public int pop() {
            return doReturn(() -> this.out.pop());
        }

        public int peek() {
            return doReturn(() -> this.out.peek());
        }

        public boolean empty() {
            return this.in.isEmpty() && this.out.isEmpty();
        }

        public int doReturn(Supplier<Integer> fun) {
            if (empty()) {
                return -1;
            }

            if (!this.out.isEmpty()) {
                return fun.get();
            }

            transferInToOut();
            return fun.get();
        }

        private void transferInToOut() {
            if (this.in.isEmpty()) {
                return;
            }

            while (!this.in.isEmpty()) {
                this.out.push(this.in.pop());
            }
        }
    }
}
