package com.bottomlord.week_017;

public class LeetCode_1016_1_子串能表示从1到N数字的二进制串 {
    public boolean queryString(String S, int N) {
        for (int i = 1; i <= N; i++) {
            String n = Integer.toBinaryString(i);
            if (!S.contains(n)) {
                return false;
            }
        }
        return true;
    }
}
