package com.bottomlord.week_061;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/9/2 8:56
 */
public class LeetCode_251_3 {
    class Vector2D {
        private Queue<Integer> queue;
        public Vector2D(int[][] v) {
            this.queue = new ArrayDeque<>();
            for (int[] arr : v) {
                for (int num : arr) {
                    this.queue.offer(num);
                }
            }
        }

        public int next() {
            return this.queue.poll();
        }

        public boolean hasNext() {
            return !this.queue.isEmpty();
        }
    }
}
