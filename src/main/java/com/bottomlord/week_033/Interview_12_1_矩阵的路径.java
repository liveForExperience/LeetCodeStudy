package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/17 12:08
 */
public class Interview_12_1_矩阵的路径 {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }

        int row = board.length, col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, row, col, i, j, word,0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int row, int col, int x, int y, String word, int index) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] != word.charAt(index)) {
            return false;
        }

        if (index + 1 == word.length()) {
            return true;
        }

        char c = board[x][y];
        board[x][y] = '#';

        boolean ans = dfs(board, row, col, x + 1, y, word, index + 1) ||
                    dfs(board, row, col, x - 1, y, word, index + 1) ||
                    dfs(board, row, col, x, y + 1, word, index + 1) ||
                    dfs(board, row, col, x, y - 1, word, index + 1);

        board[x][y] = c;
        return ans;
    }
}
