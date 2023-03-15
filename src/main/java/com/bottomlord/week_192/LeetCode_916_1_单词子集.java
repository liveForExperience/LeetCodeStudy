package com.bottomlord.week_192;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-03-15 17:32:53
 */
public class LeetCode_916_1_单词子集 {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] cnt = new int[26];
        for (String word : words2) {
            int[] cur = new int[26];
            for (char c : word.toCharArray()) {
                cur[c - 'a']++;
            }

            for (int i = 0; i < cnt.length; i++) {
                cnt[i] = Math.max(cnt[i], cur[i]);
            }
        }

        List<String> ans = new ArrayList<>();
        for (String word : words1) {
            int[] cur = new int[26];
            for (char c : word.toCharArray()) {
                cur[c - 'a']++;
            }

            boolean flag = true;
            for (int i = 0; i < cnt.length; i++) {
                if (cur[i] < cnt[i]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans.add(word);
            }
        }

        return ans;
    }
}
