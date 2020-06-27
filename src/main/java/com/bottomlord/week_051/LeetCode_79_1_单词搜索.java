package com.bottomlord.week_051;

/**
 * @author ChenYue
 * @date 2020/6/27 15:50
 */
public class LeetCode_79_1_单词搜索 {
    private int row, col;
    private char[][] board;
    private int[][] directions;
    private String word;

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }

        this.board = board;
        this.directions = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        this.row = board.length;
        this.col = board[0].length;
        this.word = word;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean ans = backtrack(i, j, 0);
                if (ans) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtrack(int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }

        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] == '#' || board[r][c] != word.charAt(index)) {
            return false;
        }


        board[r][c] = '#';
        for (int[] direction : directions) {
            boolean flag = backtrack(r + direction[0], c + direction[1], index + 1);
            if (flag) {
                return true;
            }
        }
        board[r][c] = word.charAt(index);

        return false;
    }
}
