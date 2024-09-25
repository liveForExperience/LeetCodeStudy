package com.bottomlord.week_006;

public class LeetCode_326_4 {
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
}
