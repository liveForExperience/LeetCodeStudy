package com.bottomlord.week_070;

/**
 * @author ChenYue
 * @date 2020/11/14 10:28
 */
public class LeetCode_334_2 {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int min = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) {
                min = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }

        return false;
    }
}