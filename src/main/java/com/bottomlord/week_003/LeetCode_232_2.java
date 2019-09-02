package com.bottomlord.week_003;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/25 15:55
 */
public class LeetCode_232_2 {
    class MyQueue {
        private Stack<Integer> stack;
        private Stack<Integer> tmpStack;
        public MyQueue() {
            this.stack = new Stack<>();
            this.tmpStack = new Stack<>();
        }

        public void push(int x) {
            while (!stack.isEmpty()) {
                tmpStack.push(stack.pop());
            }
            tmpStack.push(x);
            while (!tmpStack.isEmpty()) {
                stack.push(tmpStack.pop());
            }
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }
}
