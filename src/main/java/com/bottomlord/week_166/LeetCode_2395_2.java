package com.bottomlord.week_166;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-09-14 08:11:06
 */
public class LeetCode_2395_2 {
    public boolean findSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (!set.add(nums[i] + nums[i + 1])) {
                return true;
            }
        }

        return false;
    }
}
