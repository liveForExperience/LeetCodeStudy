package com.bottomlord.week_090;

/**
 * @author ChenYue
 * @date 2021/4/2 8:26
 */
public class Interview_17_21_1_直方图的水量 {
    public int trap(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len], rightMax = new int[len];
        leftMax[0] = height[0];
        rightMax[len - 1] = height[len - 1];

        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.max(Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i], 0);
        }

        return ans;
    }
}
