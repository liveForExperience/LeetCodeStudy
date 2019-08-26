package com.bottomlord.week_8;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_438_2 {
    public List<Integer> findAnagrams(String s, String p) {
        int[] bucket = new int[26];
        for (char c : p.toCharArray()) {
            bucket[c - 'a']++;
        }

        int one = 0, two = 0, len = p.length();
        char[] ss = s.toCharArray();
        List<Integer> ans = new ArrayList<>();

        while (one < ss.length) {
            if (bucket[ss[one] - 'a'] > 0) {
                bucket[ss[one++] - 'a']--;
                if (one - two == len) {
                    ans.add(two);
                }
            } else {
                bucket[ss[two++] - 'a']--;
            }
        }

        return ans;
    }
}