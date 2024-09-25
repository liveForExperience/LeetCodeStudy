package com.bottomlord.week_136;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-02-15 09:06:21
 */
public class LeetCode_offerII42_1_最近请求次数 {
    class RecentCounter {
        private final Queue<int[]> queue;
        private final Map<Integer, int[]> map;
        private int sum;
        public RecentCounter() {
            this.sum = 0;
            this.queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
            this.map = new HashMap<>();
        }

        public int ping(int t) {
            if (map.containsKey(t)) {
                int[] arr = map.get(t);
                arr[1]++;
            } else {
                int[] arr = new int[]{t, 1};
                queue.offer(arr);
                map.put(t, arr);
            }

            sum++;

            while (!queue.isEmpty() && queue.peek()[0] < t - 3000) {
                sum -= queue.poll()[1];
            }

            return sum;
        }
    }
}
