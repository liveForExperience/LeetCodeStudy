package com.bottomlord.week_236;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2024-01-17 13:37:22
 */
public class LeetCode_2744_1_最大字符串配对数目 {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        boolean[] memo = new boolean[words.length];
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            if (memo[i]) {
                continue;
            }

            String word = words[i];
            StringBuilder sb = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                sb.append(word.charAt(j));
            }

            if (map.containsKey(sb.toString()) && map.get(sb.toString()) != i) {
                memo[i] = true;
                memo[map.get(sb.toString())] = true;
                cnt++;
            }
        }

        return cnt;
    }
}
