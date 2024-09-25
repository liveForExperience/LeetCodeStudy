package com.bottomlord.week_198;

/**
 * @author chen yue
 * @date 2023-04-24 22:09:10
 */
public class LeetCode_1163_1_按字典序排在最后的子串 {
    public String lastSubstring(String s) {
        int i = 0, j = 1, n = s.length();
        while (j < n) {
            int k = 0;
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }

            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int tmp = i;
                i = j;
                j = Math.max(j + 1, tmp + k + 1);
            } else {
                j = j + k + 1;
            }
        }

        return s.substring(i, n);
    }
}
