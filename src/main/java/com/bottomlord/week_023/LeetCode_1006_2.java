package com.bottomlord.week_023;

public class LeetCode_1006_2 {
    public int clumsy(int N) {
        if (N <= 4) {
            return new int[]{1,2,6,7}[N - 1];
        } else {
            return N + new int[]{2, 2, -1, 1}[(N - 1) % 4];
        }
    }
}