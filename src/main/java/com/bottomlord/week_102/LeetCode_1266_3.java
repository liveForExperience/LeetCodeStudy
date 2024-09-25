package com.bottomlord.week_102;

public class LeetCode_1266_3 {
    public int minTimeToVisitAllPoints(int[][] points) {
        int sec = 0;
        for (int i = 0; i < points.length - 1; i++) {
            sec += getSec(points[i], points[i + 1]);
        }
        return sec;
    }

    private int getSec(int[] cur, int[] next) {
        int row = Math.abs(cur[0] - next[0]),
            col = Math.abs(cur[1] - next[1]);

        return Math.max(row, col);
    }
}
