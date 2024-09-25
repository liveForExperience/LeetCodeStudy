package com.bottomlord.week_009;

public class LeetCode_840_1_矩阵中的幻方 {
    public int numMagicSquaresInside(int[][] grid) {
        int ans = 0;
        int[] bucket = new int[9];
        for (int i = 0; i + 2 < grid.length; i++) {
            for (int j = 0; j + 2 < grid[i].length; j++) {
                if (grid[i + 1][j + 1] != 5) {
                    continue;
                }

                if (isMagic(grid[i][j], grid[i][j + 1], grid[i][j + 2],
                        grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2],
                        grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2])) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean isMagic(int... nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }

        int[] bucket = new int[max + 1];
        for (int num : nums) {
            bucket[num]++;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i] != 1) {
                return false;
            }
        }

        return nums[0] + nums[1] + nums[2] == 15 &&
                nums[3] + nums[4] + nums[5] == 15 &&
                nums[6] + nums[7] + nums[8] == 15 &&
                nums[0] + nums[3] + nums[6] == 15 &&
                nums[1] + nums[4] + nums[7] == 15 &&
                nums[2] + nums[5] + nums[8] == 15 &&
                nums[0] + nums[4] + nums[8] == 15 &&
                nums[2] + nums[4] + nums[6] == 15;
    }
}
