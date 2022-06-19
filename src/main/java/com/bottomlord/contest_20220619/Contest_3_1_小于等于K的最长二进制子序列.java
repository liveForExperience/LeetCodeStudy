package com.bottomlord.contest_20220619;

/**
 * @author chen yue
 * @date 2022-06-19 10:17:50
 */
public class Contest_3_1_小于等于K的最长二进制子序列 {
    public int longestSubsequence(String s, int k) {
        char[] cs = s.toCharArray();
        int count = 0;

        for (char c : cs) {
            if (c == '0') {
                count++;
            }
        }

        int t = 1, ans = 0;
        for (int i = cs.length - 1; i >= 0; i--) {
            char c = cs[i];
            if (c == '1' && ans + t <= k) {
                count++;
                ans += t;
            }

            t *= 2;

            if (t >= 1000000000) {
                break;
            }
        }

        return count;
    }
}
