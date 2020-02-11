package com.bottomlord.week_032;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/11 11:21
 */
public class LeetCode_873_3 {
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(A[i], i);
        }

        int[][] dp = new int[len][len];
        int ans = 0;

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                int k = A[j] - A[i];
                dp[i][j] = 2;

                if (k < A[i] && map.containsKey(k)) {
                    dp[i][j] = Math.max(dp[i][j], dp[map.get(k)][i] + 1);
                }

                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans > 2 ? ans : 0;
    }
}