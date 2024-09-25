package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/15 13:23
 */
public class LeetCode_344_2 {
    public void reverseString(char[] s) {
        int head = 0, tail = s.length - 1;
        while (head < tail) {
            s[head] ^= s[tail];
            s[tail] ^= s[head];
            s[head++] ^= s[tail++];
        }
    }
}
