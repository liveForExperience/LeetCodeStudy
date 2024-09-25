package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/24 13:16
 */
public class LeetCode_566_1_重塑矩阵 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length < r * c) {
            return nums;
        }

        int[][] ans = new int[r][c];
        int row = 0;
        int col = 0;

        for (int[] num : nums) {
            for (int i : num) {
                if (col >= c) {
                    col = 0;
                    row++;
                }

                if (row >= r) {
                    break;
                }

                ans[row][col] = i;
                col++;
            }

            if (row >= r) {
                break;
            }
        }

        return ans;
    }
}
