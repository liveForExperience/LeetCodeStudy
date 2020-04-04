package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 18:02
 */
public class LeetCode_42_2 {
    public int trap(int[] height) {
        int len = height.length;
        int[] lMax = new int[len], rMax = new int[len];
        lMax[0] = height[0];
        rMax[len - 1] = height[len - 1];
        for (int i = 1; i < len; i++) {
            lMax[i] = Math.max(height[i], lMax[i - 1]);
        }

        for (int i = len - 2; i >= 0; i--) {
            rMax[i] = Math.max(height[i], rMax[i + 1]);
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += Math.min(lMax[i], rMax[i]) - height[i];
        }
        return ans;
    }
}