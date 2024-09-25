package com.bottomlord.week_097;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/23 12:07
 */
public class LeetCode_1707_1_与数组中元素的最大异或值 {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int len = queries.length;
        int[] ans = new int[len];

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i][0], target = queries[i][1];
            int max = -1;
            for (int num : nums) {
                if (num <= target) {
                    max = Math.max(max, num ^ query);
                }
            }
            nums[i] = max;
        }

        return ans;
    }
}
