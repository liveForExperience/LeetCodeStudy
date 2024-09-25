package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-29 18:13:15
 */
public class LeetCode_1588_1_所有奇数长度子数组的和 {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }

        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + arr[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int l = 1; i + l <= n; l += 2) {
                ans += sums[i + l] - sums[i];
            }
        }

        return ans;
    }
}
