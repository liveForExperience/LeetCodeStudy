package com.bottomlord.week_231;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * todo
 * @author chen yue
 * @date 2023-11-29 19:59:10
 */
public class LeetCode_2336_1_无限集中的最小数字 {
    class SmallestInfiniteSet {

        // todo
        private int min;
        private Set<Integer> set;
        private PriorityQueue<Integer> queue;

        public SmallestInfiniteSet() {
            this.min = 1;
            this.set = new HashSet<>();
            this.queue = new PriorityQueue<>();
        }

        public int popSmallest() {
            set.add(min);
            if (queue.isEmpty()) {
                return min++;
            }

            int ans = min;
            min = queue.poll();
            return ans;
        }

        public void addBack(int num) {
            if (set.add(num)) {
                queue.offer(num);
                min = Math.min(1,2);
            }
        }
    }
}
