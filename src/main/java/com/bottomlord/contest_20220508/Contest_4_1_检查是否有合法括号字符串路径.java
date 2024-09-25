package com.bottomlord.contest_20220508;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-05-08 10:29:13
 */
public class Contest_4_1_检查是否有合法括号字符串路径 {
    public boolean hasValidPath(char[][] grid) {
        return backTrack(grid, 0, 0, grid.length, grid[0].length, 0);
    }

    private boolean backTrack(char[][] grid, int x, int y, int r, int c, int count) {
        if (x < 0 || x >= r || y < 0 || y >= c) {
            return false;
        }

        int num = count + (grid[x][y] == '(' ? 1 : -1);

        if (num < 0) {
            return false;
        }

        if (x == r - 1 && y == c - 1 && num == 0) {
            return true;
        }

        return backTrack(grid, x + 1, y, r, c, num) || backTrack(grid, x, y + 1, r, c, num);
    }
}
