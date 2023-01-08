package com.bottomlord.week_182;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2023-01-08 10:46:43
 */
public class LeetCode_2515_1_到目标字符串的最短距离 {
    public int closetTarget(String[] words, String target, int startIndex) {
        if (Objects.equals(words[startIndex], target)) {
            return 0;
        }

        int n = words.length, index = startIndex + 1, lc = 0;
        while (index != startIndex) {
            lc++;
            if (Objects.equals(words[(index + 1) % n], target)) {
                break;
            }
            index++;
        }

        if (lc == n) {
            return -1;
        }

        index = startIndex - 1;
        int rc = 0;

        while (index != startIndex) {
            rc++;
            if (Objects.equals(words[(index - 1 + n) % n], target)) {
                break;
            }
            index--;
        }

        return Math.min(lc, rc);
    }
}
