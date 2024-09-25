package com.bottomlord.week_049;

/**
 * @author ChenYue
 * @date 2020/6/13 17:49
 */
public class LeetCode_37_1_解数独 {
    private char[][] board;
    private int[][] rowMemo, colMemo, boxMemo;
    private boolean solve;

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.rowMemo = new int[9][10];
        this.colMemo = new int[9][10];
        this.boxMemo = new int[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    put(Integer.parseInt(String.valueOf(board[i][j])), i, j);
                }
            }
        }

        backTrack(0, 0);
    }

    private void backTrack(int row, int col) {
        if (solve) {
            return;
        }

        if (board[row][col] == '.') {
            for (int num = 1; num <= 9; num++) {
                if (couldPut(num, row, col)) {
                    put(num, row, col);
                    putNext(row, col);

                    if (!solve) {
                        remove(num, row, col);
                    }
                }
            }
        } else {
            putNext(row, col);
        }
    }

    private boolean couldPut(int num, int row, int col) {
        return rowMemo[row][num] + colMemo[col][num] + boxMemo[row / 3 * 3 + col / 3][num] == 0;
    }

    private void put(int num, int row, int col) {
        rowMemo[row][num]++;
        colMemo[col][num]++;
        boxMemo[row / 3 * 3 + col / 3][num]++;
        board[row][col] = (char) (num + '0');
    }

    private void remove(int num, int row, int col) {
        rowMemo[row][num]--;
        colMemo[col][num]--;
        boxMemo[row / 3 * 3 + col / 3][num]--;
        board[row][col] = '.';
    }

    private void putNext(int row, int col) {
        if (row == 8 && col == 8) {
            solve = true;
        } else {
            if (col == 8) {
                backTrack(row + 1, 0);
            } else {
                backTrack(row, col + 1);
            }
        }
    }
}
