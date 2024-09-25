package com.bottomlord.contest_20220424;

/**
 * @author chen yue
 * @date 2022-04-24 10:17:25
 */
public class Contest_2_1_统计圆内格点数目 {
    public int countLatticePoints(int[][] circles) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE,
            minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        for (int[] circle : circles) {
            int x = circle[0], y = circle[1], r = circle[2];
            minX = Math.min(x - r, minX);
            maxX = Math.max(x + r, maxX);
            minY = Math.min(y - r, minY);
            maxY = Math.max(y + r, maxY);
        }

        int count = 0;
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                for (int[] circle : circles) {
                    int x = circle[0], y = circle[1], r = circle[2];

                    if (distance(i, j, x, y) <= r) {
                        count++;
                        break;
                    }
                }
            }
        }

        return count;
    }

    private double distance(int xr, int xc, int yr, int yc) {
        return Math.sqrt(Math.pow(xr - yr, 2) + Math.pow(xc - yc, 2));
    }
}
