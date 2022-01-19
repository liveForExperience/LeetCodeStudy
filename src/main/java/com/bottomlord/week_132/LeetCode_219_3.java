package com.bottomlord.week_132;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-01-19 10:34:23
 */
public class LeetCode_219_3 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }

            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
