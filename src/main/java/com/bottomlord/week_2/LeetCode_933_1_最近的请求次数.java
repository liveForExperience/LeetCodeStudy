package com.bottomlord.week_2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/15 18:40
 */
public class LeetCode_933_1_最近的请求次数 {
    class RecentCounter {
        private Queue<Integer> queue;
        public RecentCounter() {
            this.queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            while (!this.queue.isEmpty()) {
                if (t - this.queue.peek() > 3000) {
                    this.queue.poll();
                } else {
                    break;
                }
            }

            this.queue.offer(t);
            return this.queue.size();
        }
    }
}
