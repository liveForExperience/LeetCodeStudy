package com.bottomlord.week_136;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-02-14 09:16:29
 */
public class LeetCode_offerII41_2 {
    class MovingAverage {
        private Queue<Integer> queue;
        private int size;
        private int sum;

        public MovingAverage(int size) {
            this.queue = new ArrayDeque<>();
            this.size = size;
        }

        public double next(int val) {
            queue.offer(val);
            sum += val;
            if (queue.size() > size) {
                assert !queue.isEmpty();
                sum -= queue.poll();
            }
            return sum * 1D / queue.size();
        }
    }
}
