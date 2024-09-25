package com.bottomlord.week_127;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-13 09:08:54
 */
public class LeetCode_689_1_三个无重叠子数组的最大和 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int[] arr = new int[n - k + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sums[i + k] - sums[i];
        }

        int[] left = new int[arr.length],
              right = new int[arr.length],
              ans = new int[3];

        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        int max = -1, maxIndex = -1;
        for (int i = k; i < left.length; i++) {
            if (arr[i - k] > max) {
                max = arr[i - k];
                maxIndex = i - k;
            }

            left[i] = maxIndex;
        }

        max = -1;
        maxIndex = -1;
        for (int i = right.length - 1 - k; i >= 0; i--) {
            if (arr[i + k] > max) {
                max = arr[i + k];
                maxIndex = i + k;
            }

            right[i] = maxIndex;
        }

        max = -1;
        for (int i = 0; i < arr.length; i++) {
            if (left[i] == -1 || right[i] == -1) {
                continue;
            }

            int sum = arr[i] + arr[left[i]] + arr[right[i]];
            if (sum > max) {
                max = sum;
                ans[0] = left[i];
                ans[1] = i;
                ans[2] = right[i];
            }
        }

        return ans;
    }
}
