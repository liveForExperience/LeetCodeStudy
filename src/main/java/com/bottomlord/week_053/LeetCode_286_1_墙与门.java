package com.bottomlord.week_053;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_286_1_墙与门 {
    private int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        if (row == 0) {
            return;
        }

        int col = rooms[0].length;
        if (col == 0) {
            return;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == -1) {
                    continue;
                }

                rooms[i][j] = bfs(rooms, row, col, i, j);
            }
        }
    }

    private int bfs(int[][] rooms, int row, int col, int r, int c) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[row][col];
        queue.offer(new Pair<>(r, c));
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair<Integer, Integer> pair = queue.poll();
                if (pair == null) {
                    continue;
                }

                int x = pair.getKey(), y = pair.getValue();

                if (x < 0 || x >= row || y < 0 || y >= col || rooms[x][y] == -1 || memo[x][y]) {
                    continue;
                }

                memo[x][y] = true;

                if (rooms[x][y] == 0) {
                    return count;
                }

                for (int[] diection : directions) {
                    queue.offer(new Pair<>(x + diection[0], y + diection[1]));
                }
            }
            count++;
        }
        return Integer.MAX_VALUE;
    }
}
