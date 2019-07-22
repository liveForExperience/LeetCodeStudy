package com.bottomlord.week_3;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/22 18:17
 */
public class LeetCode_575_1_分糖果 {
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();

        int len = candies.length / 2;
        for (int num : candies) {
            set.add(num);
            if (set.size() >= len) {
                return len;
            }
        }

        return set.size();
    }
}
