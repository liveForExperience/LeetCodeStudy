package com.bottomlord.week_097;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/23 15:45
 */
public class LeetCode_1707_2 {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int len = queries.length;
        int[] ans = new int[len];
        Arrays.sort(nums);

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i][0], target = queries[i][1], max = -1;
            for (int num : nums) {
                if (num <= target) {
                    max = Math.max(max, num ^ query);
                } else {
                    break;
                }
            }
            ans[i] = max;
        }
        return ans;
    }
}
