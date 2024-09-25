package com.bottomlord.week_114;

/**
 * @author chen yue
 * @date 2021-09-18 08:42:07
 */
public class LeetCode_36_2 {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9], col = new int[9], block = new int[9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                int index = c - '0';
                int shift = 1 << index;
                int blockIndex = i / 3 * 3 + j / 3;

                if ((row[i] & shift) > 0 || (col[j] & shift) > 0 || (block[blockIndex] & shift) > 0) {
                    return false;
                }

                row[i] |= shift;
                col[j] |= shift;
                block[blockIndex] |= shift;
            }
        }

        return true;
    }
}
