package com.bottomlord.week_102;

import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/6/24 20:07
 */
public class LeetCode_1260_2 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length;
        int[][] ans = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[(i + (j + k) / col) % row][(j + k) % col] = grid[i][j];
            }
        }

        List list = Arrays.asList(ans);
        return list;
    }
}
