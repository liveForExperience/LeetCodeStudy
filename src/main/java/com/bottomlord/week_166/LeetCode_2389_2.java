package com.bottomlord.week_166;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-09-14 07:58:09
 */
public class LeetCode_2389_2 {
    public int[] answerQueries(int[] nums, int[] queries) {
        int len = nums.length;
        Arrays.sort(nums);
        int[] sums = new int[len + 1], ans = new int[queries.length];

        for (int i = 0; i < len; i++) {
            sums[i + 1] += sums[i] + nums[i];
        }

        for (int i = 0; i < queries.length; i++) {
            for (int j = 1; j <= sums.length; j++) {
                if (sums[j] <= queries[i]) {
                    ans[i] = j;
                } else {
                    break;
                }
            }
        }

        return ans;
    }
}
