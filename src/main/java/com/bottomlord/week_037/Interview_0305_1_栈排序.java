package com.bottomlord.week_037;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/3/22 20:24
 */
public class Interview_0305_1_栈排序 {
    class SortedStack {
        private Stack<Integer> a;
        private Stack<Integer> b;
        public SortedStack() {
            this.a = new Stack<>();
            this.b = new Stack<>();
        }

        public void push(int val) {
            if (a.isEmpty() || val < a.peek()) {
                a.push(val);
                return;
            }

            while (!a.isEmpty() && a.peek() < val) {
                b.push(a.pop());
            }

            a.push(val);

            while (!b.isEmpty()) {
                a.push(b.pop());
            }
        }

        public void pop() {
            if (!a.isEmpty()) {
               a.pop();
            }
        }

        public int peek() {
            return a.isEmpty() ? -1 : a.peek();
        }

        public boolean isEmpty() {
            return a.isEmpty();
        }
    }
}
