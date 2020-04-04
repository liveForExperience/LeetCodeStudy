package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 12:17
 */
public class LeetCode_42_1_接雨水 {
    public int trap(int[] height) {
        int ans = 0, len = height.length;
        for (int i = 0; i < len; i++) {
            int maxL = 0, maxR = 0;

            for (int l = i - 1; l >= 0; l--) {
                maxL = Math.max(maxL, height[l]);
            }

            for (int r = i + 1; r < len; r++) {
                maxR = Math.max(maxR, height[r]);
            }

            ans += Math.max(Math.min(maxL, maxR) - height[i], 0);
        }

        return ans;
    }
}
