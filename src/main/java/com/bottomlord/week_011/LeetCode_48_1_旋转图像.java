package com.bottomlord.week_011;

public class LeetCode_48_1_旋转图像 {
    public void rotate(int[][] matrix) {
        int head = 0, tail = matrix[0].length - 1, level = 0, len = tail;
        while (head < tail) {
            for (int i = head; i < tail; i++) {
                int x = level, y = i, old = matrix[x][y], now;
                for (int time = 0; time < 4; time++) {
                    int newX = y, newY = len - x;
                    now = matrix[newX][newY];
                    matrix[newX][newY] = old;
                    old = now;
                    x = newX;
                    y = newY;
                }
            }
            head++;
            tail--;
            level++;
        }
    }
}
