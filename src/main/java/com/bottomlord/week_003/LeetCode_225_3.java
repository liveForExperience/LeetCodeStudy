package com.bottomlord.week_003;

import java.util.LinkedList;

/**
 * @author LiveForExperience
 * @date 2019/7/26 21:41
 */
public class LeetCode_225_3 {
    class MyStack {
        private LinkedList<Integer> queue;

        public MyStack() {
            this.queue = new LinkedList<>();
        }

        public void push(int x) {
            this.queue.offerFirst(x);
        }

        public int pop() {
            if (!queue.isEmpty()) {
                return queue.pollFirst();
            }
            return 0;
        }

        public int top() {
            return this.queue.peekFirst();
        }

        public boolean empty() {
            return this.queue.isEmpty();
        }
    }
}
