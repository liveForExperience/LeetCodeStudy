package com.bottomlord.week_021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeetCode_912_1_排序数组 {
    public List<Integer> sortArray(int[] nums) {
        return Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
    }
}
