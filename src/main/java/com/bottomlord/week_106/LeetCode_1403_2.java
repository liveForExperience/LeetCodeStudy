package com.bottomlord.week_106;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1403_2 {
    public List<Integer> minSubsequence(int[] nums) {
        int[] arr = new int[101];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            arr[num]++;
        }

        int count = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (arr[i] > 0 && count * 2 <= sum) {
                arr[i]--;
                count += i;
                ans.add(i);
            }
        }

        return ans;
    }
}
