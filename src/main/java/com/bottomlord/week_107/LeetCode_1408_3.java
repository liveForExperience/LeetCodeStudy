package com.bottomlord.week_107;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1408_3 {
    public List<String> stringMatching(String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (judge(word, words)) {
                list.add(word);
            }
        }

        return list;
    }

    private boolean judge(String target, String[] words) {
        for (String str : words) {
            if (str.length() > target.length() && str.contains(target)) {
                return true;
            }
        }
        return false;
    }
}
