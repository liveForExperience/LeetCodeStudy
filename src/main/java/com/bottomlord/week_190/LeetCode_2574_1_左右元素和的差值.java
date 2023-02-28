package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-02-28 16:09:07
 */
public class LeetCode_2574_1_左右元素和的差值 {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n];
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i] = preSum + nums[i];
            preSum = sum[i];
        }

        int[] ans = new int[n];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = sum[i] - nums[i] - sum[n - 1] + sum[i];
        }

        return ans;
    }
}
