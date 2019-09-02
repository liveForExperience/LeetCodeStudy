package com.bottomlord.week_007;

public class LeetCode_594_1_最长和谐子序列 {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = Integer.MIN_VALUE, min = Integer.MAX_VALUE, max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            len = Math.max(len, num);
        }

        int value = min < 0 ? Math.abs(min) : 0;

        int[] bucket = new int[len + value + 1];
        for (int num : nums) {
            bucket[num + value]++;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i - 1] != 0 || bucket[i] != 0) {
                max = Math.max(bucket[i - 1] + bucket[i], max);
            }
        }

        return max;
    }
}
