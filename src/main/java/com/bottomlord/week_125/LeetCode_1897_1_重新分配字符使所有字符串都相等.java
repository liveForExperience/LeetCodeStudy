package com.bottomlord.week_125;

/**
 * @author chen yue
 * @date 2021-12-04 14:20:09
 */
public class LeetCode_1897_1_重新分配字符使所有字符串都相等 {
    public boolean makeEqual(String[] words) {
        int n = words.length;
        int[] bucket = new int[26];

        for (String word : words) {
            for (char c : word.toCharArray()) {
                bucket[c - 'a']++;
            }
        }

        for (int num : bucket) {
            if (num % n != 0) {
                return false;
            }
        }

        return true;
    }
}
