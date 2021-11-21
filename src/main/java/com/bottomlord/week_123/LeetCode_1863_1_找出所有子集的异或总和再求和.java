package com.bottomlord.week_123;

/**
 * @author chen yue
 * @date 2021-11-20 14:56:14
 */
public class LeetCode_1863_1_找出所有子集的异或总和再求和 {
    private int ans = 0;
    public int subsetXORSum(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i, 0);
        }
        return ans;
    }

    private void dfs(int[] nums, int index, int xor) {
        if (index == nums.length) {
            return;
        }

        xor ^= nums[index];
        ans += xor;

        for (int i = index + 1; i < nums.length; i++) {
            dfs(nums, i, xor);
        }
    }
}
