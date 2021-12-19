package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-19 09:34:06
 */
public class LeetCode_491_2 {
    public int countBattleships(char[][] board) {
        int row = board.length, col = board[0].length, count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
                    count++;
                }
            }
        }

        return count;
    }
}