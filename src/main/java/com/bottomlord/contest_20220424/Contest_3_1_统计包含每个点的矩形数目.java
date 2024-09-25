package com.bottomlord.contest_20220424;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-04-24 10:17:31
 */
public class Contest_3_1_统计包含每个点的矩形数目 {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int[][] newPoints = new int[points.length][3];
        for (int i = 0; i < points.length; i++) {
            newPoints[i] = new int[]{points[i][0], points[i][1], i};
        }

        Arrays.sort(newPoints, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int[] ans = new int[points.length];
        for (int p = 0, r = 0; p < points.length && r < rectangles.length; p++) {
            int x = newPoints[p][0], y = newPoints[p][1];
            while (r < rectangles.length && rectangles[r][0] < x) {
                r++;
            }

            int count = 0;
            for (int i = r; i < rectangles.length; i++) {
                int ry = rectangles[i][1];
                if (ry >= y) {
                    count++;
                }
            }

            ans[newPoints[p][2]] = count;
        }

        return ans;
    }
}
