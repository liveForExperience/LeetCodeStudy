package com.bottomlord.week_058;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/8/11 9:05
 */
public class LeetCode_1044_1_最长重复子串 {
    public String longestDupSubstring(String S) {
        int n = S.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = S.charAt(i) - 'a';
        }

        int left = 1, right = n, a = 26, len = 0;
        long modulus = (long) Math.pow(2, 32);
        while (left < right) {
            len = left + (right - left) / 2;

            if (rabinKarp(len, a, modulus, nums) != -1) {
                left = len + 1;
            } else {
                right = len;
            }
        }

        int start = rabinKarp(left - 1, a, modulus, nums);
        return start == -1 ? "" : S.substring(start, start + len - 1);
    }

    private int rabinKarp(int l, int a, long modulus, int[] nums) {
        int n = nums.length;
        long h = 0;
        for (int i = 0; i < l; i++) {
            h = (h * a + nums[i]) % modulus;
        }

        long aL = 1;
        for (int i = 0; i < l; i++) {
            aL = (aL * a) % modulus;
        }

        Set<Long> memo = new HashSet<>();
        memo.add(h);

        for (int i = 1; i < n - l + 1; i++) {
            h = (h * a - nums[i - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[i + l - 1]) % modulus;

            if (memo.contains(h)) {
                return i;
            }

            memo.add(h);
        }

        return -1;
    }
}
