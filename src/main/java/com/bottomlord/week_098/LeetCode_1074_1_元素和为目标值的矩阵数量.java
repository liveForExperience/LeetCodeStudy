package com.bottomlord.week_098;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/5/29 14:46
 */
public class LeetCode_1074_1_元素和为目标值的矩阵数量 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length, ans = 0;
        for (int top = 0; top < row; top++) {
            int[] sums = new int[col];
            for (int bottom = 0; bottom < row; bottom++) {
                for (int c = 0; c < col; c++) {
                    sums[c] += matrix[bottom][c];
                }
            }

            ans += getTargetCount(sums, target);
        }
        return ans;
    }

    private int getTargetCount(int[] nums, int target) {
        int sum = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            ans += map.getOrDefault(sum - target, 0);
        }
        return ans;
    }
}
