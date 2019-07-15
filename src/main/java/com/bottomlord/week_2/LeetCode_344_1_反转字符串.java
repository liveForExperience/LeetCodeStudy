package com.bottomlord.week_2;

/**
 * @author LiveForExperience
 * @date 2019/7/15 13:14
 */
public class LeetCode_344_1_反转字符串 {
    public void reverseString(char[] s) {
        int head = 0, tail = s.length - 1;
        while (head < tail) {
            char tmp = s[head];
            s[head++] = s[tail];
            s[tail++] = tmp;
        }
    }
}
