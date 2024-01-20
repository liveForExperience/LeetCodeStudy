package com.bottomlord.week_236;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-01-20 14:32:11
 */
public class LeetCode_2788_1_按分隔符拆分字符串 {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> strs = new ArrayList<>();
        for (String word : words) {
            strs.addAll(split(word, separator));
        }
        return strs;
    }

    private List<String> split(String word, char separator) {
        List<String> strs = new ArrayList<>();
        char[] cs = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (c == separator) {
                if (sb.length() != 0) {
                    strs.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                sb.append(c);
            }
        }

        if (sb.length() != 0) {
            strs.add(sb.toString());
        }

        return strs;
    }
}
