package com.bottomlord.week_236;

import com.bottomlord.LeetCodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-01-20 14:55:00
 */
public class LeetCode_2788_2 {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> strs = new ArrayList<>();
        for (String word : words) {
            char[] cs = word.toCharArray();
            int l = 0, r = 0, n = cs.length;
            while (l < n) {
                while (r < n && cs[r] != separator) {
                    r++;
                }

                if (l < r) {
                    strs.add(word.substring(l, r));
                }

                l = ++r;
            }
        }

        return strs;
    }
}
