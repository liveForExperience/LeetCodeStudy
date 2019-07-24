package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/24 13:53
 */
public class LeetCode_566_2 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int oldRow = nums.length;
        int oldCol = nums[0].length;
        if (oldRow * oldCol < r * c) {
            return nums;
        }

        int row = 0;
        int col = 0;

        int[][] ans = new int[r][c];
        for (int i = 0; i < oldRow; i++) {
            for (int j = 0; j < oldCol; j++) {
                ans[row][col] = nums[i][j];

                row += ++col / c;
                col %= c;
            }
        }
        return ans;
    }
}
