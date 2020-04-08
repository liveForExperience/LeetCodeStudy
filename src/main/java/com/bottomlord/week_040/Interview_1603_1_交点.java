package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/8 8:00
 */
public class Interview_1603_1_交点 {
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int a1 = end1[1] - start1[1], b1 = start1[0] - end1[0], c1 = end1[0] * start1[1] - start1[0] * end1[1];
        int a2 = end2[1] - start2[1], b2 = start2[0] - end2[0], c2 = end2[0] * start2[1] - start2[0] * end2[1];

        if (a1 * b2 == a2 * b1) {
            if (c1 != c2) {
                return new double[0];
            }

            double[] ans = new double[]{Double.MAX_VALUE, Double.MAX_VALUE};
            int[][] arrs = new int[][]{start1, end1, start2, end2};
            for (int[] arr : arrs) {
                if (inLine(arr[0], arr[1], start1, end1, start2, end2)) {
                    if (arr[0] < ans[0] || (arr[0] == ans[0] && arr[1] < ans[1])) {
                        ans[0] = arr[0];
                        ans[1] = arr[1];
                    }
                }
            }
            return ans[0] == Double.MAX_VALUE ? new double[0] : ans;
        }

        double x = 1.0 * (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1),
                y = 1.0 * (c1 * a2 - c2 * a1) / (a1 * b2 - a2 * b1);

        if (inLine(x, y, start1, end1, start2, end2)) {
            return new double[]{x, y};
        } else {
            return new double[0];
        }
    }

    private boolean inLine(double x, double y, int[] start1, int[] end1, int[] start2, int[] end2) {
        int minX1 = Math.min(start1[0], end1[0]), maxX1 = Math.max(start1[0], end1[0]);
        int minY1 = Math.min(start1[1], end1[1]), maxY1 = Math.max(start1[1], end1[1]);
        if (x < minX1 || x > maxX1 || y < minY1 || y > maxY1) {
            return false;
        }
        int minX2 = Math.min(start2[0], end2[0]), maxX2 = Math.max(start2[0], end2[0]);
        int minY2 = Math.min(start2[1], end2[1]), maxY2 = Math.max(start2[1], end2[1]);
        if (x < minX2 || x > maxX2 || y < minY2 || y > maxY2) {
            return false;
        }

        return true;
    }
}
