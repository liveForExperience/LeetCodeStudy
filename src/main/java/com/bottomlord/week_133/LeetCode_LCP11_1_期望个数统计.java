package com.bottomlord.week_133;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-01-30 14:15:04
 */
public class LeetCode_LCP11_1_期望个数统计 {
    public int expectNumber(int[] scores) {
        Set<Integer> set = new HashSet<>();
        for (int score : scores) {
            set.add(score);
        }
        return set.size();
    }
}
