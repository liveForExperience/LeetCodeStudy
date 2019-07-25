package com.bottomlord.week_3;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/25 13:20
 */
public class LeetCode_232_1_用栈实现队列 {
    class MyQueue {
        private Stack<Integer> inStack;
        private Stack<Integer> outStack;
        public MyQueue() {
            this.inStack = new Stack<>();
            this.outStack = new Stack<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
            int ans = outStack.pop();
            while (!outStack.isEmpty()) {
                inStack.push(outStack.pop());
            }
            return ans;
        }

        public int peek() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
            int ans = outStack.peek();
            while (!outStack.isEmpty()) {
                inStack.push(outStack.pop());
            }
            return ans;
        }

        public boolean empty() {
            return inStack.isEmpty();
        }
    }
}
