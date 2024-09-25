package com.bottomlord.week_229;

/**
 * @author chen yue
 * @date 2023-11-27 19:25:29
 */
public class LeetCode_907_2 {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, mod = 1000000007, ans = 0;

        for (int i = 0; i < n; i++) {
            int pre = arr[i];
            ans += pre;
            for (int j = i + 1; j < n; j++) {
                int cur = Math.min(pre, arr[j]);
                ans += cur;
                ans %= mod;
                pre = cur;
            }
        }

        return ans;
    }
}
