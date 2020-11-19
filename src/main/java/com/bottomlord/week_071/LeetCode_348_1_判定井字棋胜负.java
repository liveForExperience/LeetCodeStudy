package com.bottomlord.week_071;

/**
 * @author ChenYue
 * @date 2020/11/18 8:52
 */
public class LeetCode_348_1_判定井字棋胜负 {
    class TicTacToe {
        private int[][] board;
        private int n;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            this.n = n;
            this.board = new int[n][n];
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            board[row][col] = player;

            boolean rw = true, cw = true, pw = true, nw = true;
            for (int i = 0; i < n; i++) {
                if (rw && board[row][i] != player) {
                    rw = false;
                }

                if (cw && board[i][col] != player) {
                    cw = false;
                }

                if (pw && board[i][i] != player) {
                    pw = false;
                }

                if (nw && board[i][n - 1 - i] != player) {
                    nw = false;
                }

                if (!rw && !cw && !pw && !nw) {
                    break;
                }
            }

            return rw || cw || pw || nw ? player : 0;
        }
    }
}
