package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-03 08:44:51
 */
public class LeetCode_42_1_接雨水 {
    public int trap(int[] height) {
        int n = height.length;
        int[] lmax = new int[n], rmax = new int[n];
        lmax[0] = height[0];
        rmax[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            lmax[i] = Math.max(lmax[i - 1], height[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(lmax[i], rmax[i]) - height[i];
        }

        return ans;
    }
}
