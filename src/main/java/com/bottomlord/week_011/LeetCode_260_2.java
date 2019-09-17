package com.bottomlord.week_011;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_260_2 {
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        Integer[] arr = set.toArray(new Integer[0]);
        return new int[]{arr[0], arr[1]};
    }
}