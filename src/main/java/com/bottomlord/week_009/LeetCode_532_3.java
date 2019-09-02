package com.bottomlord.week_009;

import java.util.Arrays;

public class LeetCode_532_3 {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        Arrays.sort(nums);
        int count = 0;

        if (k == 0) {
            int i = 0;
            while (i < nums.length) {
                if (nums[i] == nums[i + 1]) {
                    count++;
                    i = nextNum(i, nums);
                } else {
                    i++;
                }
            }
            return count;
        }

        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] - nums[slow] == k) {
                count++;
                slow = nextNum(slow, nums);
                fast = nextNum(fast, nums);
            } else if (nums[fast] - nums[slow] < k) {
                fast = nextNum(fast, nums);
            } else {
                slow = nextNum(slow, nums);
            }
        }

        return count;
    }

    private int nextNum(int i, int[] nums) {
        int j = i + 1;
        while (j < nums.length && nums[i] == nums[j]) {
            j++;
        }
        return j;
    }
}