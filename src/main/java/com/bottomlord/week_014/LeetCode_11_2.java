package com.bottomlord.week_014;

public class LeetCode_11_2 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, max = 0;
        while (left < right) {
            int l = height[left];
            int r = height[right];
            if (l < r) {
                max = Math.max(l * (right - left), max);
                left++;
            } else {
                max = Math.max(r * (right - left), max);
                right--;
            }
        }
        return max;
    }
}