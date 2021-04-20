package com.bottomlord.week_093;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2021/4/20 8:26
 */
public class LeetCode_28_2 {
    public int strStr(String haystack, String needle) {
        if (Objects.equals("", needle)) {
            return 0;
        }

        if (Objects.equals("", haystack)) {
            return -1;
        }

        if (haystack.length() < needle.length()) {
            return -1;
        }

        if (haystack.length() == needle.length()) {
            return Objects.equals(haystack, needle) ? 0 : -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            int hi = i, ni = 0;
            while (hi < haystack.length() && ni < needle.length() && haystack.charAt(hi) == needle.charAt(ni)) {
                hi++;
                ni++;
            }

            if (ni == needle.length()) {
                return i;
            }
        }

        return -1;
    }
}
