package com.bottomlord.week_109;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/8/9 8:22
 */
public class LeetCode_313_1_超级丑数 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        set.add(1L);

        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);

        long ugly = 1;
        for (int i = 0; i < n; i++) {
            ugly = queue.poll();
            for (int num : primes) {
                long next = num * ugly;
                if (set.add(next)) {
                    queue.offer(next);
                }
            }
        }

        return (int)ugly;
    }
}
