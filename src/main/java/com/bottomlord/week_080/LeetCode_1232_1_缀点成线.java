package com.bottomlord.week_080;

/**
 * @author ChenYue
 * @date 2021/1/18 8:28
 */
public class LeetCode_1232_1_缀点成线 {
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaA = coordinates[0][0], deltaB = coordinates[0][1], n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaA;
            coordinates[i][1] -= deltaB;
        }

        int a = coordinates[1][1], b = -coordinates[1][0];

        for (int i = 2; i < n; i++) {
            if (coordinates[i][0] * a + coordinates[i][1] * b != 0) {
                return false;
            }
        }

        return true;
    }
}
