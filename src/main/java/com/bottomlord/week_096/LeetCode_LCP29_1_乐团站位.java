package com.bottomlord.week_096;

import java.util.Arrays;

public class LeetCode_LCP29_1_乐团站位 {
    private int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int orchestraLayout(int num, int xPos, int yPos) {
        int[][] matrix = new int[num][num];
        for (int[] row : matrix) {
            Arrays.fill(row, 0);
        }
        recurse(matrix, num, 0, 0, xPos, yPos, 0, 0);
        return matrix[xPos][yPos];
    }

    private void recurse(int[][] matrix, int num, int x, int y, int px, int py, int index, int count) {
        matrix[x][y] = count % 9 + 1;

        if (px == x && py == y) {
            return;
        }

        int nextX = x + directions[index][0], nextY = y + directions[index][1];
        if (nextX >= num || nextX < 0 || nextY >= num || nextY < 0 || matrix[nextX][nextY] != 0) {
            index = (index + 1) % 4;
            nextX = x + directions[index][0];
            nextY = y + directions[index][1];
        }

        recurse(matrix, num, nextX, nextY, px, py, index, count + 1);
    }
}
