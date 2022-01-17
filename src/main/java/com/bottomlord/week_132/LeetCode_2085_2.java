package com.bottomlord.week_132;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-01-17 21:54:08
 */
public class LeetCode_2085_2 {
    public int countWords(String[] words1, String[] words2) {
        Set<String> oneMet = new HashSet<>(), oneDup = new HashSet<>(),
                    twoMet = new HashSet<>(), twoDup = new HashSet<>();

        int count = 0;

        for (String word : words1) {
            if (!oneMet.add(word)) {
                oneDup.add(word);
            }
        }

        for (String word : words2) {
            if (oneDup.contains(word)) {
                continue;
            }

            if (oneMet.contains(word)) {
                if (twoMet.contains(word)) {
                    if (twoDup.contains(word)) {
                        continue;
                    }

                    count--;
                    twoDup.add(word);
                    continue;
                }

                count++;
                twoMet.add(word);
            }
        }

        return count;
    }
}