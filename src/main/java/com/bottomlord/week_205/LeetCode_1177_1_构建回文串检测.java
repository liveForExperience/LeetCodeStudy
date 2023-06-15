package com.bottomlord.week_205;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-06-15 09:39:34
 */
public class LeetCode_1177_1_构建回文串检测 {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] sums = new int[n + 1][26];
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            System.arraycopy(sums[i], 0, sums[i + 1], 0, 26);
            sums[i + 1][cs[i] - 'a']++;
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            int left = query[0], right = query[1] + 1;
            int odd = 0;
            for (int i = 0; i < 26; i++) {
                if ((sums[right][i] - sums[left][i]) % 2 == 1) {
                    odd++;
                }
            }

            ans.add(odd / 2 <= query[2]);
        }

        return ans;
    }
}
