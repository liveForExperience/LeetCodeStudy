package com.bottomlord.week_115;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-09-19 21:50:46
 */
public class LeetCode_1684_1_统计一致字符串的数目 {
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            set.add(c);
        }

        int count = 0;
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (!set.contains(c)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}
