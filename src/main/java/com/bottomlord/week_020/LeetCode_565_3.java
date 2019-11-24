package com.bottomlord.week_020;

public class LeetCode_565_3 {
    public int arrayNesting(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int count = 0, num = nums[i];
                while (nums[num] != Integer.MAX_VALUE) {
                    int index = num;
                    num = nums[num];
                    count++;
                    nums[index] = Integer.MAX_VALUE;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
