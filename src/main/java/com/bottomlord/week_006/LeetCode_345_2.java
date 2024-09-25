package com.bottomlord.week_006;

public class LeetCode_345_2 {
    public String reverseVowels(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = s.length() - 1;

        while (head < tail) {
            while (head < tail && !isVowel(cs[head])) {
                head++;
            }

            while (head < tail && !isVowel(cs[tail])) {
                tail--;
            }

            if (head == tail) {
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

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
                || c == 'U';
    }
}
