package com.bottomlord.week_076;

/**
 * @author ChenYue
 * @date 2020/12/21 18:38
 */
public class LeetCode_395_1_至少有K个重复字符的最长子串 {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0 && arr[i] < k) {
                String[] strs = s.split("" + (char) (i + 'a'));
                int max = 0;
                for (String str : strs) {
                    if ("".equals(str)) {
                        continue;
                    }
                    max = Math.max(max, longestSubstring(str, k));
                }
                return max;
            }
        }

        return s.length();
    }
}
