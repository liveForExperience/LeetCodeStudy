package com.bottomlord.week_045;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/5/11 14:00
 */
public class Interview_1715_1_最长单词 {
    public String longestWord(String[] words) {
        Arrays.sort(words, (x1, x2) -> {
            if (x1.length() == x2.length()) {
                return x1.compareTo(x2);
            }

            return x2.length() - x1.length();
        });

        Set<String> set = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            if (recurse(word, 0, set)) {
                return word;
            }
        }

        return "";
    }

    private boolean recurse(String word, int start, Set<String> set) {
        if (start >= word.length()) {
            return true;
        }

        boolean flag = false;
        for (int i = start; i < word.length(); i++) {
            String tmp = word.substring(start, i + 1);

            if (!Objects.equals(tmp, word) && set.contains(word.substring(start, i + 1))) {
                flag = recurse(word, i + 1, set);
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }
}
