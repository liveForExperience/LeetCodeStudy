package com.bottomlord.week_066;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/10/18 14:24
 */
public class LeetCode_305_2 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0) {
            return ans;
        }

        int[][] matrix = new int[m][n];
        for (int i = 0; i < positions.length; i++) {
            matrix[positions[i][0]][positions[i][1]] = 1;

            int[][] tmpMatrix = new int[m][n];
            for (int j = 0; j < m; j++) {
                tmpMatrix[j] = Arrays.copyOfRange(matrix[j], 0, n);
            }
            ans.add(count(tmpMatrix));
        }

        return ans;
    }

    private int count(int[][] matrix) {
        int count = 0;
        boolean[][] memo = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                    dfs(matrix, i, j, memo);
                }
            }
        }

        return count;
    }

    private void dfs(int[][] matrix, int r, int c, boolean[][] memo) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || matrix[r][c] != 1 || memo[r][c]) {
            return;
        }

        matrix[r][c] = 0;
        memo[r][c] = true;

        dfs(matrix, r + 1, c, memo);
        dfs(matrix, r - 1, c, memo);
        dfs(matrix, r, c + 1, memo);
        dfs(matrix, r, c - 1, memo);
    }
}
