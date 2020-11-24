package com.bottomlord.week_072;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/11/24 8:22
 */
public class LeetCode_354_1_俄罗斯套娃信封问题 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        int[] dp = new int[envelopes.length];
        int ans = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
