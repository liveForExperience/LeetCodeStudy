package com.bottomlord.week_210;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-07-19 09:40:44
 */
public class LeetCode_1851_1_包含每个查询的最小区间 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length, i = 0;
        int[] ans = new int[n];
        Integer[] qis = new Integer[n];
        for (int k = 0; k < n; k++) {
            qis[k] = k;
        }

        Arrays.fill(ans, -1);
        Arrays.sort(qis, Comparator.comparingInt(x -> queries[x]));
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        for (int index = 0; index < n; index++) {
            int query = queries[qis[index]];
            while (i < intervals.length && intervals[i][0] <= query) {
                queue.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][0], intervals[i][1]});
                i++;
            }

            while (!queue.isEmpty() && queue.peek()[2] < query) {
                queue.poll();
            }

            if (!queue.isEmpty()) {
                ans[qis[index]] = queue.peek()[0];
            }
        }

        return ans;
    }
}
