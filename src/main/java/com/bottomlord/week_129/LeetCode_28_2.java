package com.bottomlord.week_129;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-12-30 18:54:26
 */
public class LeetCode_28_2 {
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

        for (int i = 0; i < lenH - lenN + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < lenN; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                return i;
            }
        }

        return -1;
    }
}