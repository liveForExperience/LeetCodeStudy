package com.bottomlord.week_008;

import java.util.Arrays;

public class LeetCode_581_1_最短无序连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1;
        for (; head < nums.length; head++) {
            if (copy[head] != nums[head]) {
                break;
            }
        }

        for (; tail >= 0; tail--) {
            if (copy[tail] != nums[tail]) {
                break;
            }
        }

        if (head > tail) {
            return 0;
        }

        return tail - head + 1;
    }
}
