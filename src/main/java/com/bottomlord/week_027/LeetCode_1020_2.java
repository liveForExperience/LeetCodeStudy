package com.bottomlord.week_027;

/**
 * @author ThinkPad
 * @date 2020/1/6 17:00
 */
public class LeetCode_1020_2 {
    public int numEnclaves(int[][] A) {
        int ans = 0;
        if (A == null || A.length <= 1 || A[0].length <= 1) {
            return 0;
        }

        int row = A.length, col = A[0].length;

        for (int i = 0; i < col; i++) {
            dfs(A, 0, i, row, col);
            dfs(A, row - 1, i, row, col);
        }

        for (int i = 0; i < row; i++) {
            dfs(A, i, 0, row, col);
            dfs(A, i, col - 1, row, col);
        }

        for (int[] ints : A) {
            for (int j = 0; j < col; j++) {
                ans += ints[j];
            }
        }

        return ans;
    }

    private void dfs(int[][] matrix, int x, int y, int row, int col) {
        if (x >= row || x < 0 || y >= col || y < 0 || matrix[x][y] == 0) {
            return;
        }

        matrix[x][y] = 0;
        dfs(matrix, x + 1, y, row, col);
        dfs(matrix, x - 1, y, row, col);
        dfs(matrix, x, y + 1, row, col);
        dfs(matrix, x, y - 1, row, col);
    }
}
