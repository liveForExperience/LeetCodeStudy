package com.bottomlord.week_102;

public class LeetCode_1266_2 {
    public int minTimeToVisitAllPoints(int[][] points) {
        int index = 0, n = points.length, step = 0, row = points[0][0], col = points[0][1];

        while (index < n) {
            if (row == points[index][0] && col == points[index][1]) {
                index++;
            }

            if (index == n) {
                break;
            }

            step++;

            if (row < points[index][0]) {
                row++;
            } else if (row > points[index][0]) {
                row--;
            }

            if (col < points[index][1]) {
                col++;
            } else if (col > points[index][1]) {
                col--;
            }
        }

        return step;
    }
}
