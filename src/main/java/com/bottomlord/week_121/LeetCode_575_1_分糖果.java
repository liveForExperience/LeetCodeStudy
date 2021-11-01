package com.bottomlord.week_121;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chen yue
 * @date 2021-11-01 08:44:25
 */
public class LeetCode_575_1_分糖果 {
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = Arrays.stream(candyType).boxed().collect(Collectors.toSet());
        return Math.min(set.size(), candyType.length / 2);
    }
}
