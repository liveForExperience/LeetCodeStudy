package com.bottomlord.week_012;

import java.util.List;

public class LeetCode_120_1_三角形最小路径和 {
    public int minimumTotal(List<List<Integer>> triangle) {
        return recurse(triangle, 0, 0, triangle.size());
    }

    private int recurse(List<List<Integer>> triangle, int level, int index, int row) {
        if (level == row - 1) {
            return triangle.get(level).get(index);
        }

        int left = recurse(triangle, level + 1, index, row);
        int right = recurse(triangle, level + 1, index + 1, row);

        return Math.min(left, right) + triangle.get(level).get(index);
    }
}
