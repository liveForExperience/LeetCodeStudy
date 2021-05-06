package com.bottomlord.week_095;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2021/5/6 17:09
 */
public class LeetCode_716_1_最大栈 {
    class MaxStack {
        private final Stack<Integer> basicStack, maxStack;
        public MaxStack() {
            basicStack = new Stack<>();
            maxStack = new Stack<>();
        }

        public void push(int x) {
            basicStack.push(x);
            maxStack.push(maxStack.isEmpty() ? x : (x > maxStack.peek() ? x : maxStack.peek()));
        }

        public int pop() {
            maxStack.pop();
            return basicStack.pop();
        }

        public int top() {
            return basicStack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            Stack<Integer> tmp = new Stack<>();
            int x = maxStack.peek();
            while (basicStack.peek() != x) {
                tmp.push(pop());
            }

            pop();

            while (!tmp.isEmpty()) {
                push(tmp.pop());
            }

            return x;
        }
    }
}
