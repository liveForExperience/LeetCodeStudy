package com.bottomlord.week_005;

public class LeetCode_217_3 {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int min = nums[0] , max = min;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num < min) {
                min = num;
            } else if (num > max) {
                max = num;
            } else {
                return true;
            }
        }

        boolean[] bucket = new boolean[max - min + 1];

        for (int num: nums) {
            if (bucket[num - min]) {
                return true;
            }
            bucket[num - min] = true;
        }

        return false;
    }
}