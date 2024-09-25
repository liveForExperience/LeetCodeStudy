package com.bottomlord.week_016;

public class LeetCode_36_1_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        for (char[] row : board) {
            int[] arr = new int[10];
            for (char c : row) {
                if (c != '.') {
                    if (arr[Character.digit(c, 10)] != 0) {
                        return false;
                    } else {
                        arr[Character.digit(c, 10)]++;
                    }
                }
            }
        }

        for (int c = 0; c < 9; c++) {
            int[] arr = new int[10];
            for (int r = 0; r < 9; r++) {
                if (board[r][c] != '.') {
                    if (arr[Character.digit(board[r][c], 10)] != 0) {
                        return false;
                    } else {
                        arr[Character.digit(board[r][c], 10)]++;
                    }
                }
            }
        }

        for (int b = 0; b < 9; b++) {
            int r = b / 3 * 3, c = b % 3 * 3, rm = r + 3, cm = c + 3;
            int[] arr = new int[10];
            for (int i = r; i < rm; i++) {
                for (int j = c; j < cm; j++) {
                    if (board[i][j] != '.') {
                        if (arr[Character.digit(board[i][j], 10)] != 0) {
                            return false;
                        } else {
                            arr[Character.digit(board[i][j], 10)]++;
                        }
                    }
                }
            }
        }

        return true;
    }
}
