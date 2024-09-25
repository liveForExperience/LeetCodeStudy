package com.bottomlord.week_073;

import java.util.ArrayDeque;

/**
 * @author ChenYue
 * @date 2020/11/30 16:50
 */
public class LeetCode_362_1_敲击计数器 {
    class HitCounter {
        private ArrayDeque<int[]> queue;
        public HitCounter() {
            queue = new ArrayDeque<>();
        }

        public void hit(int timestamp) {
            if (!queue.isEmpty()) {
                int[] last = queue.getLast();
                if (last[0] == timestamp) {
                    last[1]++;
                    return;
                }
            }

            queue.offer(new int[]{timestamp, 1});
        }

        public int getHits(int timestamp) {
            int pre5minTimestamp = timestamp - 5 * 60, count = 0;
            while (!queue.isEmpty()) {
                int[] element = queue.peek();
                if (element[0] <= pre5minTimestamp) {
                    queue.poll();
                } else {
                    break;
                }
            }

            for (int[] element : queue) {
                count += element[1];
            }

            return count;
        }
    }
}
