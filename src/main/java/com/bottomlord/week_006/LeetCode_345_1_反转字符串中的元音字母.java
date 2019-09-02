package com.bottomlord.week_006;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_345_1_反转字符串中的元音字母 {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        char[] cs = s.toCharArray();

        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (!set.contains(cs[head])) {
                head++;

                if (head >= s.length()) {
                    break;
                }
            }

            if (head >= s.length()) {
                break;
            }

            while (!set.contains(cs[tail])) {
                tail--;

                if (tail < 0) {
                    break;
                }
            }

            if (tail < 0) {
                break;
            }

            if (head >= tail) {
                break;
            }

            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];

            head++;
            tail--;
        }

        return new String(cs);
    }
}
