package com.bottomlord.week_7;

import java.util.Arrays;

public class LeetCode_594_5 {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);

        int ans = 0, i = 0;
        for (int j = 0; j < nums.length; j++) {
            while (nums[i] + 1 < nums[j]) {
                i++;
            }
            if (nums[i] + 1 == nums[j]) {
                ans = Math.max(ans, j - i + 1);
            }
        }

        return ans;
    }
}