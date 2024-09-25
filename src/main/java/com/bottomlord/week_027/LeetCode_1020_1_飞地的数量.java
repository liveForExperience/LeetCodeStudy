package com.bottomlord.week_027;

/**
 * @author ThinkPad
 * @date 2020/1/6 9:01
 */
public class LeetCode_1020_1_飞地的数量 {
    public int numEnclaves(int[][] A) {
        int ans = 0;
        if (A == null || A.length <= 1 || A[0].length <= 1) {
            return 0;
        }

        int row = A.length, col = A[0].length;

        for (int i = 0; i < col; i++) {
            if (A[0][i] == 1) {
                dfs(A, 0, i, row, col);
            }

            if (A[row - 1][i] == 1) {
                dfs(A, row - 1, i, row, col);
            }
        }

        for (int i = 0; i < row; i++) {
            if (A[i][0] == 1) {
                dfs(A, i, 0, row, col);
            }

            if (A[i][row - 1] == 1) {
                dfs(A, i, row - 1, row, col);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (A[i][j] == 1) {
                    ans += dfs(A, i, j, row, col);
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int x, int y, int row, int col) {
        if (x >= row || x < 0 || y >= col || y < 0 || matrix[x][y] == 0) {
            return 0;
        }

        matrix[x][y] = 0;
        return dfs(matrix, x + 1, y, row, col) +
                dfs(matrix, x - 1, y, row, col) +
                dfs(matrix, x, y + 1, row, col) +
                dfs(matrix, x, y - 1, row, col) + 1;
    }
}
