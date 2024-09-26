package com.bottomlord.week_272;

/**
 * @author chen yue
 * @date 2024-09-25 23:16:17
 */
public class LeetCode_2374_1_边积分最高的节点 {
    public int edgeScore(int[] edges) {
        int maxi = edges.length;
        long max = -1;
        long[] bucket = new long[edges.length];
        for (int i = 0; i < edges.length; i++) {
            bucket[edges[i]] += i;
            if (bucket[edges[i]] > max) {
                max = bucket[edges[i]];
                maxi = edges[i];
            } else if (bucket[edges[i]] == max) {
                maxi = Math.min(edges[i], maxi);
            }
        }
        return maxi;
    }
}
