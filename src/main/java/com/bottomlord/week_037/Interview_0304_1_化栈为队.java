package com.bottomlord.week_037;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/3/21 20:13
 */
public class Interview_0304_1_化栈为队 {
    class MyQueue {
        private Stack<Integer> in;
        private Stack<Integer> out;
        public MyQueue() {
            this.in = new Stack<>();
            this.out = new Stack<>();
        }

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            if (empty()) {
                return -1;
            }

            if (!out.isEmpty()) {
                return out.pop();
            }

            while (!in.isEmpty()) {
                out.push(in.pop());
            }

            return out.pop();
        }

        public int peek() {
            if (empty()) {
                return -1;
            }

            if (!out.isEmpty()) {
                return out.peek();
            }

            while (!in.isEmpty()) {
                out.push(in.pop());
            }

            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }
}
