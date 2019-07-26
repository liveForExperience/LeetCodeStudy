package com.bottomlord.week_3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/26 21:46
 */
public class LeetCode_225_2 {
    class MyStack {
        private Queue<Integer> queue;

        public MyStack() {
            this.queue = new LinkedList<>();
        }

        public void push(int x) {
            this.queue.offer(x);
            int times = queue.size();
            while (times > 1) {
                this.queue.offer(this.queue.poll());
                times--;
            }
        }

        public int pop() {
            return this.queue.poll();
        }

        public int top() {
            return this.queue.peek();
        }

        public boolean empty() {
            return this.queue.isEmpty();
        }
    }
}
