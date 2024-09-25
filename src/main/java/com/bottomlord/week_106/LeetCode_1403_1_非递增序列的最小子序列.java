package com.bottomlord.week_106;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_1403_1_非递增序列的最小子序列 {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int count = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            count += nums[i];
            ans.add(nums[i]);
            if (count > sum / 2) {
                break;
            }
        }

        return ans;
    }
}
