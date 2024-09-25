package com.bottomlord.week_075;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/12/14 8:28
 */
public class LeetCode_379_1_电话目录管理系统 {
    class PhoneDirectory {
        private PriorityQueue<Integer> pQueue;
        private Queue<Integer> queue;
        private boolean[] bucket;
        public PhoneDirectory(int maxNumbers) {
            this.pQueue = new PriorityQueue<>();
            this.queue = new LinkedList<>();
            for (int i = 0; i < maxNumbers; i++) {
                pQueue.offer(i);
            }
            this.bucket = new boolean[maxNumbers];
            Arrays.fill(bucket, true);
        }

        public int get() {
            if (pQueue.isEmpty()) {
                if (queue.isEmpty()) {
                    return -1;
                }

                while (!queue.isEmpty()) {
                    pQueue.offer(queue.poll());
                }
            }

            if (pQueue.isEmpty()) {
                return -1;
            }

            Integer num = pQueue.poll();
            bucket[num] = false;
            return num;
        }

        public boolean check(int number) {
            if (number > bucket.length) {
                return false;
            }

            return bucket[number];
        }

        public void release(int number) {
            if (check(number)) {
                return;
            }

            queue.offer(number);
            bucket[number] = true;
        }
    }
}
