package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-05 20:14:48
 */
public class LeetCode_1827_2 {
    public int minOperations(int[] nums) {
        int ans = 0, pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > pre) {
                pre = nums[i];
            } else {
                pre++;
                ans += pre - nums[i];
            }
        }

        return ans;
    }
}
