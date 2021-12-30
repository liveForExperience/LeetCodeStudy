package com.bottomlord.week_129;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-12-30 19:12:11
 */
public class LeetCode_28_3 {

    private final long pow = 31;

    public int strStr(String haystack, String needle) {
        int lenH = haystack.length(), lenN = needle.length();
        if (lenN == 0) {
            return 0;
        }

        if (lenN > lenH) {
            return -1;
        }

        if (lenN == lenH) {
            return Objects.equals(haystack, needle) ? 0 : -1;
        }

        long hashN = getStrHash(needle),
             hashH = getStrHash(haystack.substring(0, lenN));

        if (hashH == hashN) {
            return 0;
        }

        long base = 1;
        for (int i = 0; i < lenN - 1; i++) {
            base *= pow;
        }

        for (int i = lenN; i < lenH; i++) {
            hashH = (hashH - ((haystack.charAt(i - lenN) - 'a' + 1) * base)) * pow + (haystack.charAt(i) - 'a' + 1);
            if (hashH == hashN) {
                return i - lenN + 1;
            }
        }

        return -1;
    }

    private long getStrHash(String str) {
        long num = 0;
        char[] cs = str.toCharArray();
        for (char c : cs) {
            num = num * pow + (c - 'a' + 1);
        }
        return num;
    }
}
