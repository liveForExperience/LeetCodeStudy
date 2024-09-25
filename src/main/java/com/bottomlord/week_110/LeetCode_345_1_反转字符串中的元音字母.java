package com.bottomlord.week_110;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-08-19 22:58:16
 */
public class LeetCode_345_1_反转字符串中的元音字母 {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');
        char[] cs = s.toCharArray();
        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (!set.contains(cs[head]) && head < tail) {
                head++;
            }

            while (!set.contains(cs[tail]) && head < tail) {
                tail--;
            }

            char tmp = cs[head];
            cs[head] = cs[tail];
            cs[tail] = tmp;

            head++;
            tail--;
        }

        return new String(cs);
    }
}
