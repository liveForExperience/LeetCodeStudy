package com.bottomlord.week_018;

public class LeetCode_75_1_颜色分类 {
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int num : nums) {
            counts[num]++;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] > 0) {
                    nums[i] = j;
                    counts[j]--;
                    break;
                }
            }
        }
    }
}
