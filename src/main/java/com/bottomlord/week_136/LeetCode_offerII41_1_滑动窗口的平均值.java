package com.bottomlord.week_136;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-02-14 09:02:25
 */
public class LeetCode_offerII41_1_滑动窗口的平均值 {
    class MovingAverage {
        private Queue<Integer> queue;
        private int size;
        private int average;

        public MovingAverage(int size) {
            this.queue = new ArrayDeque<>();
            this.size = size;
        }

        public double next(int val) {
            queue.offer(val);
            if (queue.size() > size) {
                queue.poll();
            }

            int sum = 0;
            for (Integer num : queue) {
                sum += num;
            }

            return sum * 1D / queue.size();
        }
    }
}
