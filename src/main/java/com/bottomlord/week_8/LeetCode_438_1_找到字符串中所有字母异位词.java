package com.bottomlord.week_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_438_1_找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        int[] bucket = new int[26];
        int len = p.length();
        for (char c : p.toCharArray()) {
            bucket[c - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i + len - 1 < s.length(); i++) {
            int[] copy = Arrays.copyOf(bucket, bucket.length);
            for (int j = i; j < i + len; j++) {
                copy[s.charAt(j) - 'a']--;
            }

            boolean flag = true;
            for (int num : copy) {
                if (num != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans.add(i);
            }
        }

        return ans;
    }
}
