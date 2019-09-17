package com.bottomlord.week_011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_260_1_只出现一次的数字III {
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            if (i + 1 == nums.length) {
                list.add(nums[i]);
                break;
            }

            if (nums[i] == nums[i + 1]) {
                i += 2;
            } else {
                list.add(nums[i]);
                i++;
            }
        }
        return new int[]{list.get(0), list.get(1)};
    }
}
