package com.bottomlord.week_053;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_315_1_计算右侧小于当前元素的个数 {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            counts.add(count);
        }
        return counts;
    }
}
