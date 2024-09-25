package com.bottomlord.week_222;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-10-10 09:38:54
 */
public class LeetCode_2731_1_移动机器人 {
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] arr = new long[n];
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            long v = cs[i] == 'L' ? -1 : 1;
            arr[i] = nums[i] + v * d;
        }

        Arrays.sort(arr);
        long sum = 0, mod = 1000000007;
        for (int i = 1; i < n; i++) {
            long x = arr[i] - arr[i - 1];
            sum += i * x % mod * (n - i) % mod;
            sum %= mod;
        }

        return (int) sum;
    }
}
