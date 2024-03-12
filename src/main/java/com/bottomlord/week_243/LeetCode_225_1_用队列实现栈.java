package com.bottomlord.week_243;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2024-03-05 09:19:33
 */
public class LeetCode_225_1_用队列实现栈 {
    class MyStack {

        private Queue<Integer> in, out;

        public MyStack() {
            this.in = new ArrayDeque<>();
            this.out = new ArrayDeque<>();
        }

        public void push(int x) {
            in.offer(x);
            while (!out.isEmpty()) {
                in.offer(out.poll());
            }

            Queue<Integer> tmp = in;
            in = out;
            out = tmp;
        }

        public int pop() {
            if (out.isEmpty()) {
                return -1;
            }

            return out.poll();
        }

        public int top() {
            if (out.isEmpty()) {
                return -1;
            }

            return out.peek();
        }

        public boolean empty() {
            return out.isEmpty();
        }
    }
}
