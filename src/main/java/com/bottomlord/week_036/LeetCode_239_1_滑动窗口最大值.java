package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/9 19:36
 */
public class LeetCode_239_1_滑动窗口最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length, max = Integer.MIN_VALUE, maxIndex = -1;
        int[] ans = new int[len - k + 1];

        for (int i = 0; i < len - k + 1; i++) {
            if (maxIndex >= i && maxIndex < i + k) {
                if (nums[i + k - 1] > max) {
                    maxIndex = i + k - 1;
                    max = nums[i + k - 1];
                }
            } else {
                max = nums[i];
                for (int j = i; j < i + k; j++) {
                    if (max < nums[j]) {
                        max = nums[j];
                        maxIndex = j;
                    }
                }

            }

            ans[i] = max;
        }

        return ans;
    }
}
