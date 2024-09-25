package com.bottomlord.week_107;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1408_2 {
    public List<String> stringMatching(String[] words) {
        boolean[] memo = new boolean[words.length];
        List<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (memo[j] || i == j || words[i].length() <= words[j].length()) {
                    continue;
                }

                if (words[i].contains(words[j])) {
                    memo[j] = true;
                    list.add(words[j]);
                }
            }
        }

        return list;
    }
}
