package com.bottomlord.week_117;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-10-04 23:12:20
 */
public class LeetCode_1763_1_最长的美好子字符串 {
    public String longestNiceSubstring(String s) {
        int n = s.length(), max = 1;
        String ans = "";
        for (int i = 0; i < n - max; i++) {
            for (int j = i + max; j < n; j++) {
                if (isNice(s.substring(i, j + 1))) {
                    max = Math.max(max, j + 1 - i);
                }
            }
        }

        return ans;
    }

    private boolean isNice(String s) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add("" + s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(("" + s.charAt(i)).toUpperCase()) ||
                !set.contains(("" + s.charAt(i)).toLowerCase())) {
                return false;
            }
        }

        return true;
    }
}
