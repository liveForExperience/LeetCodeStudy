package com.bottomlord.week_077;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/12/30 16:40
 */
public class LeetCode_417_1_太平洋大西洋水流问题 {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return ans;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return ans;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int a = dfs(matrix, null, i, j, row, col, -1, -1, new boolean[row][col]);
                int b = dfs(matrix, null, i, j, row, col, row, col, new boolean[row][col]);
                if (a + b == 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, Integer pre, int r, int c, int row, int col, int targetR, int targetC, boolean[][] visited) {
        if (r == targetR || c == targetC) {
            return 1;
        }

        if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c]) {
            return 0;
        }

        if (pre != null && matrix[r][c] > pre) {
            return 0;
        }
        visited[r][c] = true;

        int down = dfs(matrix, matrix[r][c], r + 1, c, row, col, targetR, targetC, visited),
            top = dfs(matrix, matrix[r][c], r - 1, c, row, col, targetR, targetC, visited),
            right = dfs(matrix, matrix[r][c], r, c + 1, row, col, targetR, targetC, visited),
            left = dfs(matrix, matrix[r][c], r, c - 1, row, col, targetR, targetC, visited);

        if (down + top + right + left >= 1) {
            return 1;
        }

        return 0;
    }
}
