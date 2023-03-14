package com.bottomlord.week_192;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-03-14 22:52:52
 */
public class LeetCode_2586_1_统计范围内的元音字符串数 {
    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        int count = 0;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            char start = word.charAt(0), end = word.charAt(word.length() - 1);
            if (set.contains(start) && set.contains(end)) {
                count++;
            }
        }

        return count;
    }
}
