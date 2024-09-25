package com.bottomlord.week_017;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode_973_1_最接近原点的K个点 {
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        int[][] ans = new int[K][2];
        Integer[] rank = new Integer[len];
        double[] distances = new double[len];
        for (int i = 0; i < len; i++) {
            int x = points[i][0], y = points[i][1];
            distances[i] = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            rank[i] = i;
        }

        Arrays.sort(rank, Comparator.comparingDouble(x -> distances[x]));

        for (int i = 0; i < K; i++) {
            ans[i] = points[rank[i]];
        }

        return ans;
    }
}
