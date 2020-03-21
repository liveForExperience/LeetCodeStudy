package com.bottomlord.week_037;

/**
 * @author ThinkPad
 * @date 2020/3/21 15:26
 */
public class LeetCode_365_2 {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x == 0 && y == 0) {
            return z == 0;
        }
        return z == 0 || (z % gcd(x, y) == 0 && x + y >= z);
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}