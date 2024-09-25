package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-21 17:47:49
 */
public class LeetCode_1941_1_检查是否所有字符出现次数相同 {
    public boolean areOccurrencesEqual(String s) {
        int[] bucket = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            bucket[c - 'a']++;
        }

        Integer target = null;
        for (int num : bucket) {
            if (num == 0) {
                continue;
            }

            if (target == null) {
                target = num;
                continue;
            }

            if (num != target) {
                return false;
            }
        }

        return true;
    }
}
