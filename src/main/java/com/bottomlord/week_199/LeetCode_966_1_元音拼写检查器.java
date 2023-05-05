package com.bottomlord.week_199;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-05-05 17:29:38
 */
public class LeetCode_966_1_元音拼写检查器 {
    private Set<String> wordSet = new HashSet<>();
    private Map<String, String> lowercaseMap = new HashMap<>(), lowercaseNoVowelMap = new HashMap<>();
    public String[] spellchecker(String[] wordlist, String[] queries) {

        for (String word : wordlist) {
            wordSet.add(word);
            lowercaseMap.putIfAbsent(word.toLowerCase(), word);
            lowercaseNoVowelMap.putIfAbsent(handleVowel(word.toLowerCase()), word);
        }

        int n = queries.length;
        String[] ans = new String[n];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = solve(queries[i]);
        }
        return ans;
    }

    private String solve(String word) {
        if (wordSet.contains(word)) {
            return word;
        }

        if (lowercaseMap.containsKey(word.toLowerCase())) {
            return lowercaseMap.get(word.toLowerCase());
        }

        return lowercaseNoVowelMap.getOrDefault(handleVowel(word.toLowerCase()), "");
    }

    private String handleVowel(String word) {
        StringBuilder sb = new StringBuilder();
        char[] cs = word.toCharArray();
        for (char c : cs) {
            sb.append(isVowel(c) ? '*' : c);
        }
        return sb.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
