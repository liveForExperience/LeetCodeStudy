package com.bottomlord.contest_20191020;

public class Contest_1_缀点成线 {
    public boolean checkStraightLine(int[][] coordinates) {
        double a = (double) (coordinates[0][1] - coordinates[1][1]) / (coordinates[0][0] - coordinates[1][0]);
        double b = coordinates[0][1] - a * coordinates[0][0];

        for (int[] coordinate: coordinates) {
            if (coordinate[0] * a + b != coordinate[1]) {
                return false;
            }
        }

        return true;
    }
}
