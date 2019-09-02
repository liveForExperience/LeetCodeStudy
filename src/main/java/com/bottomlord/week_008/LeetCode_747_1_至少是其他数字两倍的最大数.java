package com.bottomlord.week_008;

public class LeetCode_747_1_至少是其他数字两倍的最大数 {
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int one = nums[0] > nums[1] ? 0 : 1, two = one == 0 ? 1 : 0;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[one]) {
                two = one;
                one = i;
            } else if (nums[i] > nums[two]) {
                two = i;
            }
        }

        return nums[one] >= nums[two] * 2 ? one : -1;
    }
}
