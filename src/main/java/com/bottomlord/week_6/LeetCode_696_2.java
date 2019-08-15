package com.bottomlord.week_6;

public class LeetCode_696_2 {
    public int countBinarySubstrings(String s) {
        char[] cs = s.toCharArray();
        int cur = 1, last = 0, sum = 0;

        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == cs[i - 1]) {
                cur++;
            } else {
                last = cur;
                cur = 1;
            }

            if (last >= cur) {
                sum++;
            }
        }

        return sum;
    }
}