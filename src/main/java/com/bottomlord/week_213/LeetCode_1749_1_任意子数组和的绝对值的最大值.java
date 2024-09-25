package com.bottomlord.week_213;

/**
 * @author chen yue
 * @date 2023-08-08 21:27:01
 */
public class LeetCode_1749_1_任意子数组和的绝对值的最大值 {
    public int maxAbsoluteSum(int[] nums) {
        int minDp = Integer.MAX_VALUE, maxDp = Integer.MIN_VALUE, ans = 0;
        for (int num : nums) {
            minDp = Math.min(minDp, 0) + num;
            maxDp = Math.max(maxDp, 0) + num;
            ans = Math.max(ans, Math.max(-minDp, maxDp));
        }
        return ans;
    }
}
