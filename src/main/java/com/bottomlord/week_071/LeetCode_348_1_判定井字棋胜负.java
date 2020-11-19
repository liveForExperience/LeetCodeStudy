package com.bottomlord.week_071;

/**
 * @author ChenYue
 * @date 2020/11/18 8:52
 */
public class LeetCode_348_1_判定井字棋胜负 {
    class TicTacToe {
        private int[][] board;
        private int n;

        public TicTacToe(int n) {
            this.n = n;
            this.board = new int[n][n];
        }

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
