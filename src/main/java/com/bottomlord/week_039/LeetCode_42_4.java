package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 21:19
 */
public class LeetCode_42_4 {
    public int trap(int[] height) {
        int l = 0, lMax = 0, r = height.length - 1, rMax = 0, ans = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (lMax > height[l]) {
                    ans += lMax - height[l];
                } else {
                    lMax = height[l];
                }
                l++;
            } else {
                if (rMax > height[r]) {
                    ans += rMax - height[r];
                } else {
                    rMax = height[r];
                }
                r--;
            }
        }

        return ans;
    }
}
