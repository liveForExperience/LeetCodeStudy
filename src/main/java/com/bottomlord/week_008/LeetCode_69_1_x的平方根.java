package com.bottomlord.week_008;

public class LeetCode_69_1_x的平方根 {
    public int mySqrt(int x) {
        return x == 0 ? 0 : (int)sqrt(x, x);
    }

    private double sqrt(double x, int target) {
        double ans = (x + target / x) / 2;
        return ans == x ? ans : sqrt(ans, target);
    }
}
