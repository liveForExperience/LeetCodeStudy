package com.bottomlord.week_6;

public class LeetCode_696_1_计算二进制子串 {
    public int countBinarySubstrings(String s) {
        int count0 = 0, count1 = 0, sum = 0;
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                if (flag) {
                    count0 = 0;
                    flag = false;
                }
                count0++;
                if (count0 <= count1) {
                    sum++;
                }
                continue;
            }

            if (c == '1') {
                if (!flag) {
                    count1 = 0;
                    flag = true;
                }
                count1++;
                if (count1 <= count0) {
                    sum++;
                }
            }
        }

        return sum;
    }
}
