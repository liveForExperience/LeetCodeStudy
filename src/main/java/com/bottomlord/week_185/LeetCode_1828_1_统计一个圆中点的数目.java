package com.bottomlord.week_185;

/**
 * @author chen yue
 * @date 2023-01-24 10:56:32
 */
public class LeetCode_1828_1_统计一个圆中点的数目 {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int[] center = new int[]{query[0], query[1]};
            int r = query[2];

            for (int[] point : points) {
                if (distance(point, center) <= r * r) {
                    ans[i]++;
                }
            }
        }

        return ans;
    }

    private int distance(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
