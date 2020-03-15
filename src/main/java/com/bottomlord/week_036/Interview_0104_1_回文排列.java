package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/15 10:06
 */
public class Interview_0104_1_回文排列 {
    public boolean canPermutePalindrome(String s) {
        int[] bucket = new int[256];
        for (char c : s.toCharArray()) {
            bucket[c]++;
        }

        int count = 0;
        for (int num : bucket) {
            if ((num & 1) == 1) {
                count++;
            }

            if (count > 1) {
                return false;
            }
        }

        return true;
    }
}
