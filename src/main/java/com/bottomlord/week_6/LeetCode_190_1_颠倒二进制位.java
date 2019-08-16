package com.bottomlord.week_6;

public class LeetCode_190_1_颠倒二进制位 {
    public int reverseBits(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            ans += (1 & (n >>= i) << (31 - i));
        }

        return ans;
    }
}
