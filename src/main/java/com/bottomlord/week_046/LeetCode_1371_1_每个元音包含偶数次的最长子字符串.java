package com.bottomlord.week_046;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/5/20 8:23
 */
public class LeetCode_1371_1_每个元音包含偶数次的最长子字符串 {
    public int findTheLongestSubstring(String s) {
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        pos[0] = 0;
        int ans = 0, status = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                status ^= 1;
            } else if (c == 'e') {
                status ^= 1 << 1;
            } else if (c == 'i') {
                status ^= 1 << 2;
            } else if (c == 'o') {
                status ^= 1 << 3;
            } else if (c == 'u') {
                status ^= 1 << 4;
            }

            if (pos[status] != -1) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }

        return ans;
    }
}
