package com.bottomlord.week_203;

import java.util.Set;

/**
 * @author chen yue
 * @date 2023-06-02 22:45:30
 */
public class LeetCode_2559_1_统计范围内的元音字符串数 {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < words.length; i++) {
            sums[i + 1] = sums[i] + (isVowelStr(words[i]) ? 1 : 0);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = sums[queries[i][1] + 1] - sums[queries[i][0]];
        }

        return ans;
    }

    private boolean isVowelStr(String str) {
        return isVowel(str.charAt(0)) && isVowel(str.charAt(str.length() - 1));
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
