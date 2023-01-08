package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-08 13:27:46
 */
public class LeetCode_LCP66_1_最小展台数量 {
    public int minNumBooths(String[] demand) {
        int[] bucket = new int[26];
        for (String cur : demand) {
            char[] cs = cur.toCharArray();
            int[] curBucket = new int[26];
            for (char c : cs) {
                curBucket[c - 'a']++;
            }

            for (int i = 0; i < 26; i++) {
                if (bucket[i] < curBucket[i]) {
                    bucket[i] = curBucket[i];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += bucket[i];
        }

        return ans;
    }
}
