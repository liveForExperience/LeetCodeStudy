package com.bottomlord.week_115;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-09-23 22:26:29
 */
public class LeetCode_1704_1_判断字符串的两半是否相等 {
    public boolean halvesAreAlike(String s) {
        Set<Character> set = new HashSet<>();
        char[] cs = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char c : cs) {
            set.add(c);
        }

        int half = s.length() / 2;
        String first = s.substring(0, half), second = s.substring(half, s.length());
        int count = 0;
        for (int i = 0; i < half; i++) {
            if (set.contains(first.charAt(i))) {
                count++;
            }

            if (set.contains(second.charAt(i))) {
                count--;
            }
        }

        return count == 0;
    }
}
