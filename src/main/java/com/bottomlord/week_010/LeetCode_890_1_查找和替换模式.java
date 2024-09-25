package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_890_1_查找和替换模式 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            Map<Character, Character> map1 = new HashMap<>();
            Map<Character, Character> map2 = new HashMap<>();
            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                if (map1.containsKey(pattern.charAt(i))){
                    if (map1.get(pattern.charAt(i)) != word.charAt(i)) {
                        flag = false;
                        break;
                    }
                } else if (map2.containsKey(word.charAt(i))) {
                    if (map2.get(word.charAt(i)) != pattern.charAt(i)) {
                        flag = false;
                        break;
                    }
                }else {
                    map1.put(pattern.charAt(i), word.charAt(i));
                    map2.put(word.charAt(i), pattern.charAt(i));
                }
            }

            if (flag) {
                ans.add(word);
            }
        }

        return ans;
    }
}
