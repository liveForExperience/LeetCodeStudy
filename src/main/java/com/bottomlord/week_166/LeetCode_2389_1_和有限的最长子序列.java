package com.bottomlord.week_166;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-09-14 07:44:30
 */
public class LeetCode_2389_1_和有限的最长子序列 {
    public int[] answerQueries(int[] nums, int[] queries) {
        int len = queries.length;
        int[] ans = new int[len];
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];

            int curSum = sum, count = nums.length;
            for (int j = nums.length - 1; j >= 0; j--) {
                if (curSum <= query) {
                    ans[i] = count;
                    break;
                }

                curSum -= nums[j];
                count--;
            }
        }

        return ans;
    }
}
