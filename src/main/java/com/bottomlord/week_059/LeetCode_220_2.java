package com.bottomlord.week_059;

import java.util.TreeSet;

/**
 * @author ChenYue
 * @date 2020/8/21 20:13
 */
public class LeetCode_220_2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long up = set.ceiling((long)nums[i]);
            if (up != null && up <= (long)nums[i] + t) {
                return true;
            }
            Long low = set.floor((long)nums[i]);
            if (low != null && low >= (long)nums[i] - t) {
                return true;
            }

            set.add((long)nums[i]);

            if (set.size() > k) {
                set.remove((long)nums[i - k]);
            }
        }

        return false;
    }
}