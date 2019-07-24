package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/24 13:38
 */
public class LeetCode_566_3 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        if (row * col < r * c) {
            return nums;
        }

        int[] arr = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i * col + j] = nums[i][j];
            }
        }

        int[][] ans = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = arr[i * c + j];
            }
        }

        return ans;
    }
}