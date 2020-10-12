package com.bottomlord.week_066;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/10/12 8:17
 */
public class LeetCode_296_1_最佳的碰头地点 {
    public int minTotalDistance(int[][] grid) {
        int r = grid.length;
        if (r == 0) {
            return 0;
        }
        int c = grid[0].length;
        if (c == 0) {
            return 0;
        }

        if (r == 1 && c == 1) {
            return 0;
        }

        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    points.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }

                int sum = 0;
                for (int[] point : points) {
                    sum += distance(point[0], point[1], i, j);
                }

                ans = Math.min(ans, sum);
            }
        }

        if (ans == Integer.MAX_VALUE) {
            for (int i = 0; i < points.size(); i++) {
                int sum = 0;
                for (int j = 0; j < points.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    sum += distance(points.get(i)[0], points.get(i)[1], points.get(j)[0], points.get(j)[1]);
                }
                ans = Math.min(ans, sum);
            }
        }

        return ans;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
