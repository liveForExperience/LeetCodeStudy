package com.bottomlord.week_235;

/**
 * @author chen yue
 * @date 2024-01-14 20:04:19
 */
public class LeetCode_2182_1_构造限制重复的字符串 {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] bucket = new int[26];
        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 25, j = 24; i >= 0 && j >= 0; ) {
            if (bucket[i] == 0) {
                cnt = 0;
                i--;
            } else if (cnt < repeatLimit) {
                sb.append((char) ('a' + i));
                bucket[i]--;
                cnt++;
            } else if (j >= i || bucket[j] == 0) {
                j--;
            } else {
                bucket[j]--;
                sb.append((char) ('a' + j));
                cnt = 0;
            }
        }

        return sb.toString();
    }
}