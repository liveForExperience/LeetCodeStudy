package com.bottomlord.week_024;

public class LeetCode_858_1_镜面反射 {
    public int mirrorReflection(int p, int q) {
        int lcm = p * q / gcd(p, q), y = lcm / p, x = lcm / q;
        if ((y & 1) == 0) {
            return 0;
        }

        return (x & 1) == 1 ? 1 : 2;
    }

    private int gcd(int p, int q) {
        return q == 0 ? p : gcd(q, p % q);
    }
}
