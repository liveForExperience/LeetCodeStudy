package com.bottomlord.week_035;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author ThinkPad
 * @date 2020/3/7 10:41
 */
public class Interview_59II_1_队列的最大值 {
    class MaxQueue {
        private Queue<Integer> queue;
        private Deque<Integer> max;
        public MaxQueue() {
            this.queue = new ArrayDeque<>();
            this.max = new ArrayDeque<>();
        }

        public int max_value() {
            return max.isEmpty() ? -1 : max.peek();
        }

        public void push_back(int value) {
            queue.offer(value);

            while (!max.isEmpty() && value > max.getLast()) {
                max.pollLast();
            }
            max.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }

            int value = queue.poll();
            if (value == max.peek()) {
                max.poll();
            }

            return value;
        }
    }
}
