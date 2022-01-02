package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2022-01-01 11:13:13
 */
public class LeetCode_1967_2 {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            if (pattern.length() > word.length()) {
                continue;
            }

            boolean flag = false;
            for (int i = 0; i < word.length() - pattern.length() + 1; i++) {
                if (word.charAt(i) != pattern.charAt(0)) {
                    continue;
                }

                if (word.startsWith(pattern, i)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}