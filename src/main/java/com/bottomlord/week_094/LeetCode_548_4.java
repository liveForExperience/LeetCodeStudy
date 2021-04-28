package com.bottomlord.week_094;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/4/27 16:04
 */
public class LeetCode_548_4 {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int j = 3; j < len - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 2; i + 1 < j; i++) {
                if (sums[i - 1] == sums[j - 1] - sums[i]) {
                    set.add(sums[i - 1]);
                }
            }

            for (int k = j + 1; k < len - 1; k++) {
                int tempSum = sums[k - 1] - sums[j];
                if (tempSum == sums[len - 1] - sums[k] && set.contains(tempSum)) {
                    return true;
                }
            }
        }

        return false;
    }
}
