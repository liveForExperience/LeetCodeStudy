package com.bottomlord.week_4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/8/2 22:12
 */
public class LeetCode_485_2 {
    public int findMaxConsecutiveOnes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            }

            if (num == 0) {
                set.add(count);
                count = 0;
            }
        }

        if (count != 0) {
            set.add(count);
        }
        return set.isEmpty() ? 0 : Collections.max(set);
    }
}