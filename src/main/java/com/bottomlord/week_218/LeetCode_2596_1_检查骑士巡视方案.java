package com.bottomlord.week_218;

/**
 * @author chen yue
 * @date 2023-09-13 22:31:43
 */
public class LeetCode_2596_1_检查骑士巡视方案 {
    private int[][] dirs = {{0, 0}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    public boolean checkValidGrid(int[][] grid) {
        int row = grid.length, col = grid[0].length, n = row * col;
        int[][] steps = new int[n][2];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                steps[grid[r][c]] = new int[]{r, c};
            }
        }

        int[] pre = {0, 0};
        for (int i = 0; i < n; i++) {
            int r = pre[0], c = pre[1],
                    nr = steps[i][0], nc = steps[i][1];

            boolean reach = false;
            for (int[] dir : dirs) {
                if (r + dir[0] == nr && c + dir[1] == nc) {
                    pre = steps[i];
                    reach = true;
                    break;
                }
            }

            if (!reach) {
                return false;
            }
        }

        return true;
    }
}
