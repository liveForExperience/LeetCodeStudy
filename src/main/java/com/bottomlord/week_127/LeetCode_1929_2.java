package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-19 22:09:57
 */
public class LeetCode_1929_2 {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n * 2];
        System.arraycopy(nums, 0, ans, 0, n);
        System.arraycopy(nums, 0, ans, n, n);
        return ans;
    }
}