package com.bottomlord.week_135;

/**
 * @author chen yue
 * @date 2022-02-10 09:32:45
 */
public class LeetCode_offerII12_2 {
    public int pivotIndex(int[] nums) {
        int sum = 0, cur = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if (sum == cur) {
                return i;
            }
            cur += nums[i];
        }

        return -1;
    }
}
