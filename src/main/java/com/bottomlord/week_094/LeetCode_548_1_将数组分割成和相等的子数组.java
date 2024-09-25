package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/27 9:17
 */
public class LeetCode_548_1_将数组分割成和相等的子数组 {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 1; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (sums[i - 1] == sums[j - 1] - sums[i] &&
                        sums[j - 1] - sums[i] == sums[k - 1] - sums[j] &&
                        sums[k - 1] - sums[j] == sums[len - 1] - sums[k]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
