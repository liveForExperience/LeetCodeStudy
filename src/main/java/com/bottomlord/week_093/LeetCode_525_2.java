package com.bottomlord.week_093;

import java.util.Arrays;

public class LeetCode_525_2 {
    public int findMaxLength(int[] nums) {
        int count = 0, len = nums.length, ans = 0;
        int[] arr = new int[2 * len + 1];
        Arrays.fill(arr, -2);
        arr[len] = -1;
        for (int i = 0; i < len; i++) {
            count += nums[i] == 1 ? 1 : -1;

            if (arr[count + len] >= -1) {
                ans = Math.max(ans, i - arr[count + len]);
            } else {
                arr[count + len] = i;
            }
        }

        return ans;
    }
}