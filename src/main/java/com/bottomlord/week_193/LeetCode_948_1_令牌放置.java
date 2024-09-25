package com.bottomlord.week_193;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-03-23 19:03:16
 */
public class LeetCode_948_1_令牌放置 {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int ans = 0, c = 0;

        int l = 0, r = tokens.length - 1;
        while (l <= r) {
            int cost = tokens[l];
            if (power >= cost) {
                ans -= c;
                c = 0;
                power -= cost;
                ans++;
                l++;
                continue;
            }

            if (ans > 0 && ans >= c) {
                power += tokens[r--];
                c++;
                continue;
            }

            break;
        }

        return ans;
    }
}
