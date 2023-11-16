package com.bottomlord.week_227;

/**
 * @author chen yue
 * @date 2023-11-16 18:09:25
 */
public class LeetCode_2760_1_最长奇偶子数组 {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int max = 0;
        for (int i = 0; i < nums.length;) {
            if (nums[i] % 2 == 1 || nums[i] > threshold) {
                i++;
                continue;
            }

            i++;
            boolean flag = true;
            int cur = 1;
            while (i < nums.length && (nums[i] % 2 == 0) == !flag && nums[i] <= threshold) {
                cur++;
                flag = !flag;
                i++;
            }

            max = Math.max(cur, max);
        }

        return max;
    }
}
