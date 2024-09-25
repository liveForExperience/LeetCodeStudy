package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/18 19:45
 */
public class Interview_16_2 {
    public double myPow(double x, int n) {
        double ans = recurse(x, n);
        return n > 0 ? ans : 1 / ans;
    }

    private double recurse(double x, int n) {
        if (n == 0 || x == 1) {
            return 1;
        }

        double ans = recurse(x, n / 2);
        return (n & 1) == 1 ? x * ans * ans : ans * ans;
    }
}
