package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/15 10:20
 */
public class Interview_0104_2 {
    public boolean canPermutePalindrome(String s) {
        int[] bucket = new int[256];
        int count = 0;

        for (char c : s.toCharArray()) {
            if ((bucket[c]++ & 1) == 1) {
                count++;
            } else {
                count--;
            }
        }

        return count <= 1;
    }
}