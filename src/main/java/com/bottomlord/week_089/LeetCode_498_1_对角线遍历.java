package com.bottomlord.week_089;

/**
 * @author ChenYue
 * @date 2021/3/23 11:45
 */
public class LeetCode_498_1_对角线遍历 {
    public int[] findDiagonalOrder(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return new int[0];
        }

        int col = matrix[0].length;
        if (col == 0) {
            return new int[0];
        }

        int[] ans = new int[row * col];
        recurse(matrix, ans, row, col, 0, 0, true, 0);
        return ans;
    }

    private void recurse(int[][] matrix, int[] ans, int row, int col, int r, int c, boolean flag, int index) {
        if (r == row || c == col) {
            return;
        }

        ans[index] = matrix[r][c];

        if (r == row - 1 && c == col - 1) {
            return;
        }

        if (flag) {
            if (r == 0) {
                if (c + 1 == col) {
                    recurse(matrix, ans, row, col, r + 1, c, false, index + 1);
                } else {
                    recurse(matrix, ans, row, col, r, c + 1, false, index + 1);
                }
            } else if (c == col - 1) {
                recurse(matrix, ans, row, col, r + 1, c, false, index + 1);
            } else {
                recurse(matrix, ans, row, col, r - 1, c + 1, true, index + 1);
            }
        } else {
            if (c == 0) {
                if (r + 1 == row) {
                    recurse(matrix, ans, row, col, r, c + 1, true, index + 1);
                } else {
                    recurse(matrix, ans, row, col, r + 1, c, true, index + 1);
                }
            } else if (r == row - 1) {
                recurse(matrix, ans, row, col, r, c + 1, true, index + 1);
            } else {
                recurse(matrix, ans, row, col, r + 1, c - 1, false, index + 1);
            }
        }
    }
}
