package com.bottomlord.week_006;

public class LeetCode_231_2 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}