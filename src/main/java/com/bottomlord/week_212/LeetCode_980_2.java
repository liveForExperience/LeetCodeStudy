package com.bottomlord.week_212;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-08-05 15:59:32
 */
public class LeetCode_980_2 {
    private final int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int r, c;
    private final Map<Integer, Integer> memo = new HashMap<>();
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        this.r = grid.length;
        this.c = grid[0].length;
        int startX = -1, startY = -1, status = 0;
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (grid[x][y] == 1) {
                    startX = x;
                    startY = y;
                    status = mask(status, x, y);
                } else if (grid[x][y] == 0 || grid[x][y] == 2) {
                    status = mask(status, x, y);
                }
            }
        }

        return backTrack(startX, startY, status);
    }

    private int backTrack(int x, int y, int status) {
        if (!isValid(status, x, y)) {
            return 0;
        }

        status = unmask(status, x, y);

        if (grid[x][y] == 2) {
            return status == 0 ? 1 : 0;
        }

        int key = ((x * c + y) << (r * c)) + status;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int ans = 0;
        for (int[] direction : directions) {
            int nx = direction[0], ny = direction[1];
            ans += backTrack(x + nx, y + ny, status);
        }
        memo.put(key, ans);
        return ans;
    }

    private boolean isValid(int status, int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c  && !visited(status, x, y);
    }

    private boolean visited(int status, int x, int y) {
        return (status & (1 << (x * c + y))) == 0;
    }

    private int mask(int status, int x, int y) {
        status = status | (1 << (x * c + y));
        System.out.println(Integer.toBinaryString(status));
        return status;
    }

    private int unmask(int status, int x, int y) {
        status = status ^ (1 << (x * c + y));
        System.out.println(Integer.toBinaryString(status));
        return status;
    }
}
