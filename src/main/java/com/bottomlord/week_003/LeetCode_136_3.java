package com.bottomlord.week_003;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/22 17:04
 */
public class LeetCode_136_3 {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length / 2 + 1);
        for (int num: nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        return set.iterator().next();
    }
}