package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/17 12:09
 */
public class Interview_13_1_机器人的运动范围 {
    public int movingCount(int m, int n, int k) {
        return dfs(new int[m][n], m,  n, 0, 0, k);
    }

    private int dfs(int[][] board, int row, int col, int x, int y, int k) {
        if (!isValid(board, row, col, x, y, k)) {
            return 0;
        }

        board[x][y] = 1;
        return 1 + dfs(board, row, col, x + 1, y, k) +
                dfs(board, row, col, x - 1, y, k) +
                dfs(board, row, col, x, y + 1, k) +
                dfs(board, row, col, x, y - 1, k);
    }

    private boolean isValid(int[][] board, int row, int col, int x, int y, int k) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] == 1) {
            return false;
        }

        int a = 0;
        while (x > 0) {
            a += x % 10;
            x /= 10;
        }

        int b = 0;
        while (y > 0) {
            b += y % 10;
            y /= 10;
        }

        return a + b <= k;
    }
}
