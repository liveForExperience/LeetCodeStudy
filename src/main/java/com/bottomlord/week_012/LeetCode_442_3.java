package com.bottomlord.week_012;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_442_3 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length < 2) {
            return ans;
        }

        Arrays.sort(nums);

        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                count++;
            } else  {
                if (count == 2) {
                    ans.add(nums[i]);
                }
                count = 1;
            }
        }

        if (count == 2) {
            ans.add(nums[nums.length - 2]);
        }

        return ans;
    }
}