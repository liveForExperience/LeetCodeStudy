package com.bottomlord.week_074;

public class LeetCode_376_1_摆动序列 {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int[] up = new int[len], down = new int[len];
        up[0] = down[0] = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i] - nums[i - 1] > 0) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] - nums[i - 1] < 0) {
                down[i] = Math.max(down[i - 1], up[i - 1] + 1);
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[len - 1], down[len - 1]);
    }
}
