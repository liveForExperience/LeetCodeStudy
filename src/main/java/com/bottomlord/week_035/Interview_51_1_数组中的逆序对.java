package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/3 8:52
 */
public class Interview_51_1_数组中的逆序对 {
    public int reversePairs(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
