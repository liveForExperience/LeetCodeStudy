package com.bottomlord.week_051;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/6/28 8:30
 */
public class LeetCode_209_1_长度最小的子数组 {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int[] arr = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            arr[i] = arr[i - 1] + nums[i - 1];
         }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {
            int target = s + arr[i - 1];
            int index = Arrays.binarySearch(arr, target);

            if (index < 0) {
                index = -index - 1;
            }

            ans = Math.min(ans, index - i + 1);
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
