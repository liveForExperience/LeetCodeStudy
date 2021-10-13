package com.bottomlord.week_118;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-10-13 23:15:23
 */
public class LeetCode_1805_1_字符串中不同整数的数量 {
    public int numDifferentIntegers(String word) {
        char[] cs = word.toCharArray();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (!Character.isDigit(c)) {
                continue;
            }

            if (c - '0' == 0) {
                if (i == cs.length - 1 || !Character.isDigit(cs[i + 1])) {
                    set.add("0");
                }
                continue;
            }

            StringBuilder sb = new StringBuilder();
            while (i < cs.length && Character.isDigit(cs[i])) {
                sb.insert(0, cs[i]);
                i++;
            }

            set.add(sb.toString());
        }

        return set.size();
    }
}
