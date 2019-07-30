package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/7/30 10:58
 */
public class LeetCode_812_1_最大三角形面积 {
    public double largestTriangleArea(int[][] points) {
        double max = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                for (int k = 0; k < points.length; k++) {
                    double a = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                    double b = Math.sqrt(Math.pow(points[j][0] - points[k][0], 2) + Math.pow(points[j][1] - points[k][1], 2));
                    double c = Math.sqrt(Math.pow(points[k][0] - points[i][0], 2) + Math.pow(points[k][1] - points[i][1], 2));

                    if (a + b == c || b + c == a || a + c == b) {
                        continue;
                    }

                    double p = (a + b + c) /2;
                    double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));

                    if (Double.isNaN(area)) {
                        continue;
                    }

                    max = Math.max(area, max);
                }
            }
        }

        return max;
    }
}
