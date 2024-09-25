package com.bottomlord.week_056;

/**
 * @author ChenYue
 * @date 2020/7/28 8:42
 */
public class LeetCode_159_2 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length(), head = 0, tail = 0, count = 0, ans = 0;
        int[] bucket = new int[128];

        while (tail < len) {
            char c = s.charAt(tail++);
            if (bucket[c]++ == 0) {
                count++;
            }

            ans = Math.max(ans, tail - head);

            while (count > 2) {
                if (--bucket[s.charAt(head++)] == 0) {
                    count--;
                }
            }
        }

        return Math.max(ans, tail - head);
    }
}