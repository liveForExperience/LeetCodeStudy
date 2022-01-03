package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-03 10:01:37
 */
public class LeetCode_1974_1_使用特殊打字机键入单词的最少时间 {
    public int minTimeToType(String word) {
        int start = 0, ans = 0;
        char[] cs = word.toCharArray();
        for (char c : cs) {
            ans++;
            int index = c - 'a';
            if (index > start) {
                ans += Math.min(index - start, start + 26 - index);
            } else if (index < start) {
                ans += Math.min(start - index, index + 26 - start);
            }
            start = c - 'a';
        }

        return ans;
    }
}
