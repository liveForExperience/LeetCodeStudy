package com.bottomlord.week_094;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/4/30 8:37
 */
public class LeetCode_137_1_只出现一次的数字II {
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toMap(k -> k, k -> 1, Integer::sum)).entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst().orElse(0);
    }
}
