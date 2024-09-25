package com.bottomlord.week_054;

/**
 * @author ChenYue
 * @date 2020/7/13 8:36
 */
public class LeetCode_130_1_被围绕的区域 {
    private int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0) {
            return;
        }

        int col = board[0].length;
        if (col == 0) {
            return;
        }

        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
               dfs(board, row, col, 0, i);
            }

            if (board[row - 1][i] == 'O') {
                dfs(board, row, col, row - 1, i);
            }
        }

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(board, row, col, i, 0);
            }

            if (board[i][col - 1] == 'O') {
                dfs(board, row, col, i, col - 1);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col, int r, int c) {
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] == 'X' || board[r][c] == '#') {
            return;
        }

        board[r][c] = '#';

        for (int[] direction : directions) {
            dfs(board, row, col, r + direction[0], c + direction[1]);
        }
    }
}
