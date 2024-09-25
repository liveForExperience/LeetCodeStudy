package com.bottomlord.week_175;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-11-20 08:30:54
 */
public class LeetCode_891_1_子序列宽度之和 {
    public int sumSubseqWidths(int[] nums) {
        int mod = 1000000007, n = nums.length;
        Arrays.sort(nums);
        long count = 0;
        long[] pows = new long[n];
        pows[0] = 1;
        for (int i = 1; i < n; i++) {
            pows[i] = pows[i - 1] * 2 % mod;
        }

        for (int i = 0; i < nums.length; i++) {
            count += ((pows[i] - pows[n - 1 - i]) * nums[i]) % mod;
        }

        return (int) (count % mod + mod) % mod;
    }
}
