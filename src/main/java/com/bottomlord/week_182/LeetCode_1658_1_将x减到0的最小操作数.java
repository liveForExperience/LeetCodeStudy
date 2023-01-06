package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-07 05:18:07
 */
public class LeetCode_1658_1_将x减到0的最小操作数 {

    private int ans = Integer.MAX_VALUE;

    public int minOperations(int[] nums, int x) {
        int ans = dfs(x, 0, nums.length - 1, 0, nums);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dfs(int x, int l, int r, int count, int[] nums) {
        if (count > ans) {
            return Integer.MAX_VALUE;
        }

        if (l > r) {
            return Integer.MAX_VALUE;
        }

        if (x < nums[l] && x < nums[r]) {
            return Integer.MAX_VALUE;
        }

        if (x == nums[l] || x == nums[r]) {
            return count + 1;
        }

        int ans = -1;
        if (x < nums[l]) {
            return dfs(x - nums[r], l, r - 1, count + 1, nums);
        }

        if (x < nums[r]) {
            return dfs(x - nums[l], l + 1, r, count + 1, nums);
        }

        return Math.min(
                dfs(x - nums[r], l, r - 1, count + 1, nums),
                dfs(x - nums[l], l + 1, r, count + 1, nums)
        );
    }
}
