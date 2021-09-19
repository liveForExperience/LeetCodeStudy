package com.bottomlord.week_113;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-09-06 22:13:53
 */
public class LeetCode_1624_2 {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] lefts = new int[26], rights = new int[26];
        Arrays.fill(lefts, -1);
        Arrays.fill(rights, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (lefts[c - 'a'] == -1) {
                lefts[c - 'a'] = i;
            }

            rights[c - 'a'] = i;
        }

        int ans = -1;
        for (int i = 0; i < 26; i++) {
            if (lefts[i] == rights[i]) {
                continue;
            }

            ans = Math.max(ans, rights[i] - lefts[i] - 1);
        }

        return ans;
    }
}
