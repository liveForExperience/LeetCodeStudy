package com.bottomlord.week_008;

public class LeetCode_14_1_最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        int len = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            len = Math.min(len, strs[i].length());
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            boolean equal = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                count++;
            }
        }

        return strs[0].substring(0, count);
    }
}
