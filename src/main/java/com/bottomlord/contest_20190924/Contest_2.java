package com.bottomlord.contest_20190924;

public class Contest_2 {
    public int[] fraction(int[] cont) {
        int len = cont.length, numerator = 1, denominator = cont[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            numerator ^= denominator;
            denominator ^= numerator;
            numerator ^= denominator;

            denominator = numerator * cont[i] + denominator;
            int gcd = gcd(numerator, denominator);

            numerator /= gcd;
            denominator /= gcd;
        }
        return new int[]{denominator, numerator};
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}