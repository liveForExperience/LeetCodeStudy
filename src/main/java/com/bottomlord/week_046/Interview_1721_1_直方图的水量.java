package com.bottomlord.week_046;

/**
 * @author ChenYue
 * @date 2020/5/18 8:55
 */
public class Interview_1721_1_直方图的水量 {
    public int trap(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0, rightMax = 0, curIndex = i;
            while (--curIndex > 0) {
                leftMax = Math.max(leftMax, height[curIndex]);
            }

            curIndex = i;
            while (++curIndex < height.length) {
                rightMax = Math.max(rightMax, height[curIndex]);
            }

            int min = Math.min(leftMax, rightMax);
            if (min > height[i]) {
                ans += min - height[i];
            }
        }

        return ans;
    }
}
