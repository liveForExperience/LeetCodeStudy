package com.bottomlord.week_008;

public class LeetCode_28_1_实现strStr {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        if (haystack == null || haystack.length() == 0) {
            return -1;
        }

        int index = 0, needleLen = needle.length(), hayLen = haystack.length();
        char c = needle.charAt(0);
        while (haystack.indexOf(c, index) != -1) {
            if (index + needleLen - 1 >= hayLen) {
                return -1;
            }

            boolean find = true;
            for (int i = 0; i < needleLen; i++) {
                if (needle.charAt(i) != haystack.charAt(index + i)) {
                    find = false;
                }
            }

            if (find) {
                return index;
            }

            index++;
        }

        return -1;
    }
}
