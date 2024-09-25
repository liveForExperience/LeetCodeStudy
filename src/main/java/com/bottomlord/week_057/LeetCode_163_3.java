package com.bottomlord.week_057;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/3 8:58
 */
public class LeetCode_163_3 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        long index = lower;
        for (int i = 0; i < nums.length;) {
            if (nums[i] == index) {
                i++;
            } else {
                list.add(index + nums[i] - 1 == index ? "" : "->" + (nums[i] - 1));
                index = nums[i++];
            }
        }

        if (nums[nums.length - 1] < upper) {
            list.add(nums[nums.length - 1] + 1 + upper == nums[nums.length - 1] + 1 ? "" : "->" + upper);
        }

        return list;
    }
}
