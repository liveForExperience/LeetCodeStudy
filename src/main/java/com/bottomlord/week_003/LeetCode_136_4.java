package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/22 17:13
 */
public class LeetCode_136_4 {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}