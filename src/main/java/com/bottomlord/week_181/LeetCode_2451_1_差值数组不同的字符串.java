package com.bottomlord.week_181;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-12-29 11:52:08
 */
public class LeetCode_2451_1_差值数组不同的字符串 {
    public String oddString(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        int wordLen = words[0].length();
        for (int i = 1; i < wordLen; i++) {
            Map<Integer, List<String>> map = new HashMap<>();

            for (String word : words) {
                map.computeIfAbsent(getDiff(word, i), x -> new ArrayList<>()).add(word);
            }

            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                if (entry.getValue().size() == words.length) {
                    break;
                }

                if (entry.getValue().size() == 1) {
                    return entry.getValue().get(0);
                }
            }
        }

        return null;
    }

    private int getDiff(String word, int index) {
        return word.charAt(index) - word.charAt(index - 1);
    }
}
