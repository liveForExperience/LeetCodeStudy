package com.bottomlord.week_206;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-06-21 17:11:47
 */
public class LeetCode_LCP41_1_黑白翻转棋 {

    private int row, col;
    private String[] chessboard;
    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

    public int flipChess(String[] chessboard) {
        int ans = 0;
        this.row = chessboard.length;
        this.col = chessboard[0].length();
        this.chessboard = chessboard;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (chessboard[r].charAt(c) != '.') {
                    continue;
                }

                ans = Math.max(ans, bfs(r, c));
            }
        }

        return ans;
    }

    private int bfs(int i, int j) {
        char[][] cs = new char[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                cs[r][c] = chessboard[r].charAt(c);
            }
        }

        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        cs[i][j] = 'X';

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            for (int[] dir : dirs) {
                int nr = dir[0], nc = dir[1];
                if (isValid(x, y, nr, nc, cs)) {
                    int a = x + nr, b = y + nc;
                    while (cs[a][b] != 'X') {
                        queue.offer(new int[]{a, b});
                        cs[a][b] = 'X';
                        a += nr;
                        b += nc;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean isValid(int x, int y, int nr, int nc, char[][] cs) {
        x += nr;
        y += nc;
        while (x >= 0 && x < row && y >= 0 && y < col) {
            if (cs[x][y] == 'X') {
                return true;
            } else if (cs[x][y] == '.') {
                return false;
            }

            x += nr;
            y += nc;
        }

        return false;
    }
}
