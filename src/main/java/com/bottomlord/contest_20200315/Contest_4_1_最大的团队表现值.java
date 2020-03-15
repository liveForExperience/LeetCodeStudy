package com.bottomlord.contest_20200315;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ThinkPad
 * @date 2020/3/15 17:23
 */
public class Contest_4_1_最大的团队表现值 {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Integer[] indexs = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }

        Arrays.sort(indexs, (o1, o2) -> efficiency[o2] - efficiency[o1]);
        long sum = 0, total = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int e = efficiency[indexs[i]];
            int s = speed[indexs[i]];

            if (i < k) {
                total += s;
                queue.offer(s);
            } else if (s > queue.peek()){
                total = total - queue.poll() + s;
                queue.offer(s);
            }

            sum = Math.max(sum, total * e);
        }

        return (int) (sum % 1000000007);
    }
}
