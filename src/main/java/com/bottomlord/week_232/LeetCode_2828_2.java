package com.bottomlord.week_232;

import java.util.List;

/**
 * @author chen yue
 * @date 2023-12-20 09:07:22
 */
public class LeetCode_2828_2 {
    public boolean isAcronym(List<String> words, String s) {
        int n = words.size();
        if (n != s.length()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}