package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/27 15:43
 */
public class LeetCode_1014_1_最佳观光组合 {
    public int maxScoreSightseeingPair(int[] A) {
        int ans = Integer.MIN_VALUE, len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                ans = Math.max(ans, A[i] + A[j] + i - j);
            }
        }
        return ans;
    }
}
