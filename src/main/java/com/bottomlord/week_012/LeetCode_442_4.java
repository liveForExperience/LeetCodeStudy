package com.bottomlord.week_012;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_442_4 {
    public List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;
        List<Integer> ans = new ArrayList<>();

        for (int num : nums) {
            int index = (num -  1) % len;
            nums[index] += len;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 2 * len) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}