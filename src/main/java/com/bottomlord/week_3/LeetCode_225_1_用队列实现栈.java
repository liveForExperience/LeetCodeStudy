package com.bottomlord.week_3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/26 17:20
 */
public class LeetCode_225_1_用队列实现栈 {
    class MyStack {
        private Queue<Integer> queue;
        private Queue<Integer> tmpQueue;
        private int top;

        public MyStack() {
            this.queue = new ArrayDeque<>();
            this.tmpQueue = new ArrayDeque<>();
        }

        public void push(int x) {
            this.queue.offer(x);
            this.top = x;
        }

        public int pop() {
            while (this.queue.size() > 1) {
                this.top = queue.poll();
                this.tmpQueue.offer(this.top);
            }

            int num = this.queue.poll();
            Queue<Integer> q = this.queue;
            this.queue = this.tmpQueue;
            this.tmpQueue = q;
            return num;
        }

        public int top() {
            return this.top;
        }

        public boolean empty() {
            return this.queue.isEmpty() && this.tmpQueue.isEmpty();
        }
    }
}
