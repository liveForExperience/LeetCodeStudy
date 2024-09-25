package com.bottomlord.week_208;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-07-04 21:12:45
 */
public class LeetCode_2679_1_矩阵中的和 {
    public int matrixSum(int[][] nums) {
        for (int[] arr : nums) {
            Arrays.sort(arr);
        }

        int ans = 0;
        int row = nums.length, col = nums[0].length;
        for (int i = 0; i < col; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < row; j++) {
                max = Math.max(max, nums[j][i]);
            }
            ans += max;
        }

        return ans;
    }
}
