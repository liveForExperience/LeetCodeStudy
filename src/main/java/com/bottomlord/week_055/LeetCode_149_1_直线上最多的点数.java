package com.bottomlord.week_055;

/**
 * @author ChenYue
 * @date 2020/7/21 8:36
 */
public class LeetCode_149_1_直线上最多的点数 {
    public int maxPoints(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return len;
        }

        int index = 0;
        for (; index < len - 1; index++) {
            if (points[index][0] != points[index + 1][0] || points[index][1] != points[index + 1][1]) {
                break;
            }
        }

        if (index == len - 1) {
            return len;
        }

        int max = 0;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x1 = points[i][0], y1 = points[i][1],
                    x2 = points[j][0], y2 = points[j][1];

                if (x1 == x2 && y1 == y2) {
                    continue;
                }

                int count = 2;

                for (int k = 0; k < len; k++) {
                    if (k != i && k != j && isLine(points[k][0], points[k][1], x1, y1, x2, y2)) {
                        count++;
                    }
                }

                max = Math.max(max, count);
            }
        }

        return max;
    }

    private boolean isLine(int x, int y, int x1, int y1, int x2, int y2) {
        int gcd1 = gcd(y2 - y1,  x2 - x1);
        if (x == x2 && y == y2) {
            return true;
        }
        int gcd2 = gcd(y - y2, x - x2);

        return (y2 - y1) / gcd1 == (y - y2) / gcd2 && (x2 - x1) / gcd1 == (x - x2) / gcd2;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
