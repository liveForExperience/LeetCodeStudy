package com.bottomlord.week_070;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/11/11 8:29
 */
public class LeetCode_514_1_自由之路 {
    public int findRotateSteps(String ring, String key) {
        int m = key.length(), n = ring.length();
        int[][] dp = new int[m][n];
        List<Integer>[] pos = new List[26];

        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            pos[ring.charAt(i) - 'a'].add(i);
        }

        for (int[] arr : dp) {
            Arrays.fill(arr, 0x3f3f3f);
        }

        for (int i = 0; i < pos[key.charAt(0) - 'a'].size(); i++) {
            int index = pos[key.charAt(0) - 'a'].get(i);
            dp[0][index] = Math.min(index, n - index) + 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }

        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }
}
