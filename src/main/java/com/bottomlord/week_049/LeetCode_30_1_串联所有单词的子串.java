package com.bottomlord.week_049;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/6/9 8:30
 */
public class LeetCode_30_1_串联所有单词的子串 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return ans;
        }

        int wordLen = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            Map<String, Integer> tmp = new HashMap<>();
            int num = 0;
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);

                if (!map.containsKey(word)) {
                    break;
                }

                tmp.put(word, tmp.getOrDefault(word, 0) + 1);
                if (tmp.get(word) > map.get(word)) {
                    break;
                }

                num++;
            }

            if (num == wordNum) {
                ans.add(i);
            }
        }

        return ans;
    }
}
