package com.bottomlord.week_182;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2023-01-08 11:10:44
 */
public class LeetCode_2515_2 {
    public int closetTarget(String[] words, String target, int startIndex) {
        int ans = 0, n = words.length;
        while (!Objects.equals(target, words[(startIndex + ans) % n]) &&
               !Objects.equals(target, words[(startIndex - 1 + n) % n])) {
            ans++;
            if (ans > n / 2) {
                return -1;
            }
        }

        return ans;
    }
}
