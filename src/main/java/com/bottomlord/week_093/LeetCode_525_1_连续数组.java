package com.bottomlord.week_093;

public class LeetCode_525_1_连续数组 {
    public int findMaxLength(int[] nums) {
        int len = nums.length, oneSum = 0, zeroSum = 0;
        int[] ones = new int[len], zeros = new int[len];

        for (int i = 0; i < len; i++) {
            ones[i] = oneSum += (nums[i] == 1 ? 1 : 0);
            zeros[i] = zeroSum += (nums[i] == 1 ? 0 : 1);
        }

        for (int l = len; l > 0; l--) {
            for (int i = 0; i + l <= len; i++) {
                int one = ones[i + l - 1] - ones[i] + (nums[i] == 1 ? 1 : 0),
                    zero = zeros[i + l - 1] - zeros[i] + (nums[i] == 1 ? 0 : 1);

                if (one == zero) {
                    return l;
                }
            }
        }

        return 0;
    }
}
