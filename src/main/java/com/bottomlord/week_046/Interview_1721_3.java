package com.bottomlord.week_046;

/**
 * @author ChenYue
 * @date 2020/5/19 8:43
 */
public class Interview_1721_3 {
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) {
            return 0;
        }

        int[] pre = new int[len], suf = new int[len];
        pre[0] = height[0];
        suf[len - 1] = height[len - 1];

        for (int i = 1; i < len - 1; i++) {
            pre[i] = Math.max(pre[i - 1], height[i]);
        }

        for (int i = len - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.max(0, Math.min(pre[i - 1], suf[i + 1]) - height[i]);
        }

        return ans;
    }
}