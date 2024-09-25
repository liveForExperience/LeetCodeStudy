package com.bottomlord.week_077;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/12/31 8:26
 */
public class LeetCode_417_2 {
    int row, col;
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        row = matrix.length;
        col = matrix[0].length;

        int[][] pac = new int[row][col], atl = new int[row][col];
        for (int i = 0; i < row; i++) {
            dfs(matrix, i, 0, pac);
            dfs(matrix, i, col - 1, atl);
        }

        for (int i = 0; i < col; i++) {
            dfs(matrix, 0, i, pac);
            dfs(matrix, row - 1, i, atl);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pac[i][j] == 1 && atl[i][j] == 1) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] matrix, int r, int c, int[][] memo) {
        memo[r][c] = 1;

        for (int[] dir : dirs) {
            int newR = r + dir[0], newC = c + dir[1];
            if (newR < 0 || newR >= row || newC < 0 || newC >= col || memo[newR][newC] == 1 || matrix[r][c] > matrix[newR][newC]) {
                continue;
            }

            dfs(matrix, newR, newC, memo);
        }
    }
}