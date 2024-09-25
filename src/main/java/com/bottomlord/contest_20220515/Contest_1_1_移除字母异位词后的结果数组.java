package com.bottomlord.contest_20220515;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-05-15 10:24:08
 */
public class Contest_1_1_移除字母异位词后的结果数组 {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);
        for (int i = 1; i < n; i++) {
            if (!check(words[i - 1], words[i])) {
                ans.add(words[i]);
            }
        }

        return ans;
    }

    private boolean check(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        char[] csa = a.toCharArray(), csb = b.toCharArray();
        int[] bucket = new int[26];
        for (char c : csa) {
            bucket[c - 'a']++;
        }

        for (char c : csb) {
            bucket[c - 'a']--;
        }

        for (int i : bucket) {
            if (i != 0){
                return false;
            }
        }

        return true;
    }
}
