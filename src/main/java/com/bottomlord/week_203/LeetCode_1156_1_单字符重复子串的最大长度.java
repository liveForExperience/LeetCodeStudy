package com.bottomlord.week_203;

/**
 * @author chen yue
 * @date 2023-06-03 08:35:35
 */
public class LeetCode_1156_1_单字符重复子串的最大长度 {
    public int maxRepOpt1(String text) {
        int[] cnt = new int[26];
        char[] cs = text.toCharArray();
        for (char c : cs) {
            cnt[c - 'a']++;
        }

        int n = cs.length, max = 0;
        for (int i = 0; i < n;) {
            int j = i;
            char c = cs[i];
            while (j < n && c == cs[j]) {
                j++;
            }

            int cur = j - i;
            if (cnt[c - 'a'] > cur || (j > 0 && j < n)) {
                max = Math.max(cur + 1, max);
            }

            int k = j + 1;
            while (k < n && c == cs[k]) {
                k++;
            }

            max = Math.max(max, Math.min(k - i, cnt[c - 'a']));

            i = j;
        }

        return max;
    }
}
