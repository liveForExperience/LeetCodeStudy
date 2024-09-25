package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/27 16:12
 */
public class LeetCode_1014_2 {
    public int maxScoreSightseeingPair(int[] A) {
        int len = A.length, max = Integer.MIN_VALUE, ans = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, max + A[i] - i);
            max = Math.max(max, A[i] + i);
        }
        return ans;
    }
}