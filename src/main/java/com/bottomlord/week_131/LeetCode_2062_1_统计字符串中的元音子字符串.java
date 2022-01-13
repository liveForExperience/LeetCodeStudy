package com.bottomlord.week_131;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-01-13 20:44:54
 */
public class LeetCode_2062_1_统计字符串中的元音子字符串 {
    public int countVowelSubstrings(String word) {
        Set<Character> memo = new HashSet<>();
        memo.add('a');
        memo.add('e');
        memo.add('i');
        memo.add('o');
        memo.add('u');
        int n = word.length();

        int target = 1 | (1 << ('e' - 'a')) | (1 << ('i' - 'a')) | (1 << ('o' - 'a')) | (1 << ('u' - 'a')),
            sum = 0;
        for (int i = 0; i < n; i++) {
            if (!memo.contains(word.charAt(i))) {
                continue;
            }

            int num = 0, count = 0;
            for (int j = i; j < n; j++) {
                if (!memo.contains(word.charAt(j))) {
                    break;
                }

                num |= (word.charAt(j) - 'a');

                if (num == target) {
                    count++;
                }
            }

            sum += count;
        }

        return sum;
    }
}
