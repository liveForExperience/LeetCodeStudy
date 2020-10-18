package com.bottomlord.week_066;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/10/18 14:10
 */
public class LeetCode_305_1_岛屿数量II {
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
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                    dfs(matrix, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(int[][] matrix, int r, int c) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || matrix[r][c] != 1) {
            return;
        }

        matrix[r][c] = 0;

        dfs(matrix, r + 1, c);
        dfs(matrix, r - 1, c);
        dfs(matrix, r, c + 1);
        dfs(matrix, r, c - 1);
    }
}
