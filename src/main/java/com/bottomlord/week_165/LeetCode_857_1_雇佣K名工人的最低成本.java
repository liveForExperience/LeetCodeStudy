package com.bottomlord.week_165;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-09-11 20:34:49
 */
public class LeetCode_857_1_雇佣K名工人的最低成本 {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double totalQ = 0, ans = Long.MAX_VALUE;
        int len = quality.length;

        Integer[] idxes = new Integer[len];
        for (int i = 0; i < len; i++) {
            idxes[i] = i;
        }
        Arrays.sort(idxes, (x, y) -> wage[x] * quality[y] - wage[y] * quality[x]);

        Queue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k - 1; i++) {
            totalQ += quality[idxes[i]];
            queue.offer(quality[idxes[i]]);
        }

        for (int i = k - 1; i < len; i++) {
            int index = idxes[i];
            queue.offer(quality[index]);
            totalQ += quality[index];
            ans = Math.min(ans, (double) wage[index] / quality[index] * totalQ);
            if (!queue.isEmpty()) {
                totalQ -= queue.poll();
            }
        }

        return ans;
    }
}
