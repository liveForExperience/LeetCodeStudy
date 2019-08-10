package com.bottomlord.week_5;

public class LeetCode_917_1_仅仅反转字母 {
    public String reverseOnlyLetters(String S) {
        char[] cs = S.toCharArray();
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            if (Character.isLetter(cs[head]) && Character.isLetter(cs[tail])) {
                cs[head] ^= cs[tail];
                cs[tail] ^= cs[head];
                cs[head] ^= cs[tail];

                head++;
                tail--;
                continue;
            }

            if (Character.isLetter(cs[head])) {
                tail--;
            } else {
                head++;
            }
        }
        return new String(cs);
    }
}
