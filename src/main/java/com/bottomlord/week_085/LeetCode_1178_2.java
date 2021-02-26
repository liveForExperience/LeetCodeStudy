package com.bottomlord.week_085;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/2/26 12:33
 */
public class LeetCode_1178_2 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> ans = new ArrayList<>();
        HashSet<Character>[] wordDict = new HashSet[words.length];
        for (int i = 0; i < words.length; i++) {
            wordDict[i] = new HashSet();
            for (char c : words[i].toCharArray()) {
                wordDict[i].add(c);
            }
        }

        for (int i = 0; i < puzzles.length; i++) {
            boolean[] pDict = new boolean[26];
            for (char c : puzzles[i].toCharArray()) {
                pDict[c - 'a'] = true;
            }

            int count = 0;
            for (HashSet<Character> wDict : wordDict) {
                if (!wDict.contains(puzzles[i].toCharArray()[0])) {
                    continue;
                }

                boolean miss = false;
                for (Character c : wDict) {
                    if (!pDict[c - 'a']) {
                        miss = true;
                        break;
                    }
                }

                if (!miss) {
                    count++;
                }
            }

            ans.add(count);
        }

        return ans;
    }
}
