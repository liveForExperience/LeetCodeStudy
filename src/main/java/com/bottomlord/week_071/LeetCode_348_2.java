package com.bottomlord.week_071;

/**
 * @author ChenYue
 * @date 2020/11/19 8:42
 */
public class LeetCode_348_2 {
    class TicTacToe {
        private int[] rows, cols;
        private int n, pie, na;

        public TicTacToe(int n) {
            this.n = n;
            this.pie = 0;
            this.na = 0;
            this.rows = new int[n];
            this.cols = new int[n];
        }

        public int move(int row, int col, int player) {
            if (row == col) {
                na = player == 1 ? na + 1 : na - 1;
            }

            if (row + col == n - 1) {
                pie = player == 1 ? pie + 1: pie - 1;
            }

            rows[row] = player == 1 ? rows[row] + 1 : rows[row] - 1;
            cols[col] = player == 1 ? cols[col] + 1 : cols[col] - 1;

            if (Math.abs(na) == n) {
                return na > 0 ? 1 : 2;
            }

            if (Math.abs(pie) == n) {
                return pie > 0 ? 1 : 2;
            }

            if (Math.abs(rows[row]) == n) {
                return rows[row] > 0 ? 1 : 2;
            }

            if (Math.abs(cols[col]) == n) {
                return cols[col] > 0 ? 1 : 2;
            }

            return 0;
        }
    }
}
