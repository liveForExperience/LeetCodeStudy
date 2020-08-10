package com.bottomlord.week_058;

/**
 * @author ChenYue
 * @date 2020/8/10 8:53
 */
public class LeetCode_186_2 {
    public void reverseWords(char[] s) {
        int len = s.length;

        if (len < 2) {
            return;
        }

        int head = 0;
        reserve(s, head, len - 1);

        for (int i = 0; i < len; i++) {
            if (s[i] == ' ') {
                reserve(s, head, i - 1);
                head = i + 1;
            }
        }

        if (head < len) {
            reserve(s, head, len - 1);
        }
    }

    private void reserve(char[] s, int head, int tail) {
        while (head < tail) {
            char c = s[head];
            s[head] = s[tail];
            s[tail] = c;

            head++;
            tail--;
        }
    }
}
