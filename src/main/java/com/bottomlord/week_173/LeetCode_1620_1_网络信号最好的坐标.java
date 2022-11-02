package com.bottomlord.week_173;

/**
 * @author chen yue
 * @date 2022-11-02 22:31:00
 */
public class LeetCode_1620_1_网络信号最好的坐标 {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, max = 0, ax = 0, ay = 0;
        for (int[] tower : towers) {
            int x = tower[0], y = tower[1];
            maxX = Math.max(maxX, x + radius);
            maxY = Math.max(maxY, y + radius);
        }

        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                int[] coordinate = {x, y};
                int cur = 0;
                for (int[] tower : towers) {
                    int squareDistance = getSquareDistance(tower, coordinate);

                    if (squareDistance <= radius * radius) {
                        double distance = Math.sqrt(squareDistance);
                        cur += tower[2] / (1 + distance);
                    }
                }

                if (cur > max) {
                    ax = x;
                    ay = y;
                    max = cur;
                }
            }
        }

        return new int[]{ax, ay};
    }

    private int getSquareDistance(int[] towers, int[] coordinate) {
        return (towers[0] - coordinate[0]) * (towers[0] - coordinate[0]) + (towers[1] - coordinate[1]) * (towers[1] - coordinate[1]);
    }
}
