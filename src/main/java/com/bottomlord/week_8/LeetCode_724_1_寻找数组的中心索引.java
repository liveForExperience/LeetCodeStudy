package com.bottomlord.week_8;

public class LeetCode_724_1_寻找数组的中心索引 {
    public int pivotIndex(int[] nums) {
        int sum = 0 , pre = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (2 * pre + nums[i] == sum) {
                return i;
            }

            pre += nums[i];
        }

        return -1;
    }
}
