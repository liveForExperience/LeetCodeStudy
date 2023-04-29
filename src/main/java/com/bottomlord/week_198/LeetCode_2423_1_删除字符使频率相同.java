package com.bottomlord.week_198;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-04-29 14:07:49
 */
public class LeetCode_2423_1_删除字符使频率相同 {
    public boolean equalFrequency(String word) {
        int[] bucket = new int[26];
        for (char c : word.toCharArray()) {
            bucket[c - 'a']++;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            bucket[i]--;

            Set<Integer> set = new HashSet<>();
            for (int num : bucket) {
                if (num == 0) {
                    continue;
                }

                set.add(num);
            }

            if (set.size() == 1) {
                return true;
            }

            bucket[i]++;
        }

        return false;
    }
}
