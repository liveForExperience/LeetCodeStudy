package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-19 22:06:56
 */
public class LeetCode_1929_1_数组串联 {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i] = ans[i + n] = nums[i];
        }
        return ans;
    }
}
