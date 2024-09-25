package com.bottomlord.week_009;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LeetCode_414_1_第三大的数 {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        if (set.size() < 3) {
            return Collections.max(set);
        }

        for (int i = 0; i < 2; i++) {
            set.remove(Collections.max(set));
        }

        return Collections.max(set);
    }
}
