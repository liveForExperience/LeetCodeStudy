package com.bottomlord.week_005;

/**
 * @author LiveForExperience
 * @date 2019/8/6 13:14
 */
public class LeetCode_286_2 {
    public int missingNumber(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans ^= i ^ nums[i];
        }
        return ans;
    }
}