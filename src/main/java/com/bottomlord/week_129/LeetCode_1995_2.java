package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2021-12-29 09:21:05
 */
public class LeetCode_1995_2 {
    public int countQuadruplets(int[] nums) {
        int[] arr = new int[10000];
        int n = nums.length, count = 0;
        for (int b = 1; b < n; b++) {
            for (int a = 0; a < b; a++) {
                arr[nums[a] + nums[b]]++;
            }

            for (int d = b + 2; d < n; d++) {
                count += arr[nums[d] - nums[b + 1]];
            }
        }

        return count;
    }
}
