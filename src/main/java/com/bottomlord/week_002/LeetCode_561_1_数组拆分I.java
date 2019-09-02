package com.bottomlord.week_002;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/16 18:35
 */
public class LeetCode_561_1_数组拆分I {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for(int i = 0; i < nums.length; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}
