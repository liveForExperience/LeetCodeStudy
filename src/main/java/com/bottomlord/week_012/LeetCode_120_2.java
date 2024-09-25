package com.bottomlord.week_012;

import java.util.List;

public class LeetCode_120_2 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        return recurse(triangle, new Integer[row][row], 0, 0, row);
    }

    private int recurse(List<List<Integer>> triangle, Integer[][] memo, int level, int index, int row) {
        if (memo[level][index] != null) {
            return memo[level][index];
        }

        if (level == row - 1) {
            return memo[level][index] = triangle.get(level).get(index);
        }

        int left = recurse(triangle, memo, level + 1, index, row);
        int right = recurse(triangle, memo, level + 1, index + 1, row);

        return memo[level][index] = Math.min(left, right) + triangle.get(level).get(index);
    }
}