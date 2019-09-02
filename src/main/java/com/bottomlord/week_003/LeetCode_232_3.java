package com.bottomlord.week_003;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/25 16:24
 */
public class LeetCode_232_3 {
    class MyQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;
        public MyQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        public void push(int x) {
            pushStack.push(x);
        }

        public int pop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }

            return popStack.pop();
        }

        public int peek() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }

            return popStack.peek();
        }

        public boolean empty() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }
    }
}
