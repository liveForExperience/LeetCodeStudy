package com.bottomlord.week_057;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/3 8:44
 */
public class LeetCode_163_2 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        int index = 0, len = nums.length;
        for (int i = lower; i <= upper;) {
            if (index < len && i == nums[index]) {
                index++;
                i++;
            } else {
                int start = i;
                i++;
                String end = "";
                if (index >= len) {
                    end = "->" + upper;
                    i = upper + 1;
                } else if (i != nums[index]) {
                    end = "->" + (nums[index] - 1);
                    i = nums[index];
                }
                list.add(start + end);
            }
        }

        return list;
    }
}