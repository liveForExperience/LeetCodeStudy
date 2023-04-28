package com.bottomlord.week_198;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2023-04-28 18:59:10
 */
public class LeetCode_1048_1_最长字符串链 {
    public int longestStrChain(String[] words) {
        int max = 0;
        Map<String, Integer> memo = new HashMap<>();
        for (String word : words) {
            memo.put(word, 0);
        }

        for (String word : words) {
            max = dfs(word, memo);
        }
        return max;
    }

    private int dfs(String s, Map<String, Integer> memo) {
        if (s.length() == 0) {
            return 0;
        }

        if (memo.get(s) > 0) {
            return memo.get(s);
        }

        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            String t = s.substring(0, i) + s.substring(i + 1);
            if (!memo.containsKey(t)) {
                continue;
            }

            max = Math.max(max, dfs(t, memo) + 1);
        }

        memo.put(s, max);
        return max;
    }
}
