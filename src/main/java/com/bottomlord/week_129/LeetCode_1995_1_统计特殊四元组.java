package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2021-12-29 08:45:02
 */
public class LeetCode_1995_1_统计特殊四元组 {
    public int countQuadruplets(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        int sum = nums[i] + nums[j] + nums[k];
                        if (sum == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
