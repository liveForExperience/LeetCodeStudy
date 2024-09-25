package com.bottomlord.week_104;

/**
 * @author ChenYue
 * @date 2021/7/8 8:09
 */
public class LeetCode_930_1_和相同的二元子数组 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (sum[i] - sum[j] == goal) {
                    count++;
                }
            }
        }
        return count;
    }
}
