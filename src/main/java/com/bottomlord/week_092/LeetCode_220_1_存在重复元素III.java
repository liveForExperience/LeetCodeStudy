package com.bottomlord.week_092;

import java.util.TreeSet;

public class LeetCode_220_1_存在重复元素III {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceilNum = set.ceiling((long) nums[i] - t);
            if (ceilNum != null && ceilNum <= (long) nums[i] + t) {
                return true;
            }

            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
