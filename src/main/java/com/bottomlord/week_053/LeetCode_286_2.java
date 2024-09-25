package com.bottomlord.week_053;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_286_2 {
    private int[][] diections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        if (row == 0) {
            return;
        }

        int col = rooms[0].length;
        if (col == 0) {
            return;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i,j});
                }
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] pair = queue.poll();
                if (pair == null) {
                    continue;
                }

                for (int[] direction : diections) {
                    int x = pair[0] + direction[0],
                        y = pair[1] + direction[1];

                    if (!isValid(x, y, row, col) || rooms[x][y] != Integer.MAX_VALUE) {
                        continue;
                    }

                    rooms[x][y] = count + 1;
                    queue.offer(new int[]{x, y});
                }
            }

            count++;
        }
    }

    private boolean isValid(int x, int y, int row, int col) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}