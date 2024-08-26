package com.bottomlord.week_268;

/**
 * @author chen yue
 * @date 2024-08-26 15:03:45
 */
public class LeetCode_3146_1_两个字符串的排列差 {
    public int findPermutationDifference(String s, String t) {
        int[] bucket = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int is = s.charAt(i) - 'a', it = t.charAt(i) - 'a';
            bucket[is] = Math.abs(i - bucket[is]);
            bucket[it] = Math.abs(i - bucket[it]);
        }

        int sum = 0;
        for (int num : bucket) {
            sum += num;
        }
        return sum;
    }
}
