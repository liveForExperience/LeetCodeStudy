package com.bottomlord.week_8;

public class LeetCode_754_2 {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int n = (int) Math.sqrt((double) target * 2.0);
        while (sum(n) < target) {
            n++;
        }

        int diff = sum(n) - target;
        if (diff % 2 == 0) {
            return n;
        }

        return n + 1 + n % 2;
    }

    private int sum(int n) {
        return (1 + n) * n / 2;
    }
}