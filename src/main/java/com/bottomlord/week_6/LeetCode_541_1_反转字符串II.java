package com.bottomlord.week_6;

public class LeetCode_541_1_反转字符串II {
    public String reverseStr(String s, int k) {
        int index = 0;
        char[] cs = s.toCharArray();
        while (index + 2 * k <= s.length() - 1) {
            int head = index, tail = index + k - 1;
            reserve(cs, head, tail);
            index += 2 * k;
        }

        if (index < s.length() - 1) {
            int tail = Math.min(index + k - 1, s.length() - 1);
            reserve(cs, index, tail);
        }

        return new String(cs);
    }

    private void reserve(char[] cs, int head, int tail) {
        while (head < tail) {
            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];

            head++;
            tail--;
        }
    }
}
