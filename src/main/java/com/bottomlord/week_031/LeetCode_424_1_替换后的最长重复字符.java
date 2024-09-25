package com.bottomlord.week_031;

/**
 * @author ThinkPad
 * @date 2020/2/7 17:27
 */
public class LeetCode_424_1_替换后的最长重复字符 {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, count = 0, ans = 0;
        int[] bucket = new int[26];

        while (right < s.length()) {
            count = Math.max(count, ++bucket[s.charAt(right) - 'A']);
            while (right - left + - count > k) {
                bucket[s.charAt(left++) - 'A']--;
            }

            ans = Math.max(ans, right++ - left + 1);
        }

        return ans;
    }
}
