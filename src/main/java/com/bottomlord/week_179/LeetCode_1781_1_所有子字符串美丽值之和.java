package com.bottomlord.week_179;

/**
 * @author chen yue
 * @date 2022-12-20 23:04:28
 */
public class LeetCode_1781_1_所有子字符串美丽值之和 {
    public int beautySum(String s) {
        int ans = 0;
        char[] cs = s.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < cs.length; j++) {
                cnt[cs[j] - 'a']++;
                maxFreq = Math.max(maxFreq, cnt[cs[j] - 'a']);

                int minFreq = maxFreq;
                for (int value : cnt) {
                    if (value > 0) {
                        minFreq = Math.min(minFreq, value);
                    }
                }

                ans += maxFreq - minFreq;
            }
        }

        return ans;
    }
}
