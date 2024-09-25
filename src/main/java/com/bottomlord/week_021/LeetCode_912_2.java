package com.bottomlord.week_021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_912_2 {
    public List<Integer> sortArray(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }
}
