package com.bottomlord.contest_20220501;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-05-01 00:34:35
 */
public class Contest_4_1_字符串的总引力 {
    public long appealSum(String s) {
        int len = s.length();
        int[] pos = new int[26];
        Arrays.fill(pos, -1);

        long curSum = 0, ans = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            curSum += i - pos[c - 'a'];
            pos[c - 'a'] = i;
            ans += curSum;
        }

        return ans;
    }
}
