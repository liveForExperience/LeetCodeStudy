package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-05 19:59:23
 */
public class LeetCode_1827_1_最少操作使数组递增 {
    public int minOperations(int[] nums) {
        int pre = nums[0], ans = 0;
        for (int i = 1; i < nums.length; i++) {
            ans += Math.max(0, pre + 1 - nums[i]);
            pre = Math.max(pre + 1, nums[i]);
        }
        return ans;
    }
}
