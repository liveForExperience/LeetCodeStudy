package com.bottomlord.week_122;

/**
 * @author chen yue
 * @date 2021-11-08 22:49:41
 */
public class LeetCode_1848_1_到目标元素的最小距离 {
    public int getMinDistance(int[] nums, int target, int start) {
        int dis = Integer.MAX_VALUE;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == target) {
                dis = i - start;
                break;
            }
        }

        for (int i = start; i >= 0; i--) {
            if (start - i >= dis) {
                break;
            }

            if (nums[i] == target) {
                return start - i;
            }
        }

        return dis;
    }
}
