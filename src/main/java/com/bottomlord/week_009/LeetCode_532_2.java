package com.bottomlord.week_009;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_532_2 {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        Set<Integer> save = new HashSet<>();
        Set<Integer> diff = new HashSet<>();
        for (int num : nums) {
            if (save.contains(num + k)) {
                diff.add(num);
            }

            if (save.contains(num - k)) {
                diff.add(num - k);
            }

            save.add(num);
        }

        return diff.size();
    }
}