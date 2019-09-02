package com.bottomlord.week_005;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_217_1_存在重复元素 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
