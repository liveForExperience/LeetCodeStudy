package com.bottomlord.week_020;

public class LeetCode_565_1_数组嵌套 {
    public int arrayNesting(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0, start = nums[i];
            do {
                start = nums[start];
                count++;
            } while (start != nums[i]);
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
