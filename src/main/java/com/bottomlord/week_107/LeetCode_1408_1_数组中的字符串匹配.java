package com.bottomlord.week_107;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode_1408_1_数组中的字符串匹配 {
    public List<String> stringMatching(String[] words) {
        Set<Integer> memo = new HashSet<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j || memo.contains(j)) {
                    continue;
                }

                if (words[i].contains(words[j])) {
                    memo.add(j);
                    list.add(words[j]);
                }
            }
        }

        return list;
    }
}
