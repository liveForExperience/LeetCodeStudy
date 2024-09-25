package com.bottomlord.week_041;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/17 8:26
 */
public class Interview_1619_1_水域大小 {
    public int[] pondSizes(int[][] land) {
        List<Integer> ans = new ArrayList<>();
        int row = land.length, col = land[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (land[i][j] == 0) {
                    ans.add(dfs(land, i, row, j, col));
                }
            }
        }
        return ans.stream().mapToInt(x -> x).sorted().toArray();
    }

    private int dfs(int[][] land, int x, int row, int y, int col) {
        if (x < 0 || x >= row || y < 0 || y >= col || land[x][y] != 0) {
            return 0;
        }

        land[x][y] = -1;

        return dfs(land, x + 1, row, y, col) +
                dfs(land, x + 1, row, y - 1, col) +
                dfs(land, x + 1, row, y + 1, col) +
                dfs(land, x - 1, row, y, col) +
                dfs(land, x - 1, row, y + 1, col) +
                dfs(land, x - 1, row, y - 1, col) +
                dfs(land, x, row, y + 1, col) +
                dfs(land, x, row, y - 1, col) + 1;
    }
}
