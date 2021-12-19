package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-19 09:07:41
 */
public class LeetCode_419_1_甲板上的战舰 {
    private final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int countBattleships(char[][] board) {
        int row = board.length, col = board[0].length, count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    dfs(board, row, col, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] board, int row, int col, int r, int c) {
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] != 'X') {
            return;
        }

        board[r][c] = '.';

        for (int[] direction : directions) {
            dfs(board, row, col, r + direction[0], c + direction[1]);
        }
    }
}
