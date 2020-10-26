package com.bottomlord.week_068;

/**
 * @author ChenYue
 * @date 2020/10/26 8:30
 */
public class LeetCode_1365_1_有多少小于当前数字的数字 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int num : nums) {
                if (nums[i] > num) {
                    ans[i]++;
                }
            }
        }

        return ans;
    }
}
